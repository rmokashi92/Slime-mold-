
img <- readJPEG(system.file("img", "Rlogo.jpg", package="jpeg"))

library(jpeg)
im<-readJPEG("copy copy.jpg",FALSE)

nw<-im[,,1]
rotate <- function(x) t(apply(x, 2, rev))
rotate(x)
nw1<-(nw<0.7)*1
#image(rotate(nw1))
image(nw)
#write.csv(rotate(N1), file="network", row.names=F, col.names=F)
# remove the edge line
for (k in 1:3){
  N1=matrix(NA,201, 210)
  for(i in 1:nrow(nw1) ){ 
    for(j in 1:ncol(nw1)) {   
     
      if(i==1 | j==1  | i==nrow(nw1) | j==ncol(nw1)){     
        N1[i,j]=0 } else {   
    if( ((nw1[i-1,j]==0) | (nw1[i,j-1]==0) |(nw1[i+1,j]==0) | (nw1[i,j+1]==0))){
        N1[i,j]=0 }else{ N1[i,j]=nw1[i,j]}
                 }
      }
  }
   nw1<-N1
}
image(N1)

# shrink the nodes to single pixel.
for (k in 1:3){
  N1=matrix(NA,201, 210)
  for(i in 1:nrow(nw1) ){ 
    for(j in 1:ncol(nw1)) {   
      
      if(i==1 | j==1  | i==nrow(nw1) | j==ncol(nw1)){     
        N1[i,j]=0 } else {   
          if( ((nw1[i-1,j]==0) | (nw1[i,j-1]==0) | (nw1[i+1,j]==0) | 
              (nw1[i,j+1]==0) | (nw1[i-1,j-1]==0) | (nw1[i+1,j-1]==0) | 
              (nw1[i-1,j+1]==0) | (nw1[i+1,j+1]==0)) &
              !((nw1[i-1,j]==0) & (nw1[i,j-1]==0) & (nw1[i+1,j]==0) & 
                (nw1[i,j+1]==0) & (nw1[i-1,j-1]==0) & (nw1[i+1,j-1]==0) & 
                (nw1[i-1,j+1]==0) & (nw1[i+1,j+1]==0))){
            N1[i,j]=0 ;nw1[i,j]=0}else{ N1[i,j]=nw1[i,j]}
        }
    }
  }
  nw1<-N1
}

image(N1)
#  need clean out 3 extract pixel mannually.

# record the coordinates by the posittion of pixel matrix
nodes=matrix(NA,36,2)
k=0
for(i in 1:nrow(nw1) ){ 
  for(j in 1:ncol(nw1)) {  
    
    if( N1[i,j]==1){
      k=k+1
      nodes[k,1]= i
      nodes[k,2]= j
   }
   }
}

#  need clean out 3 extract pixel mannually.
plot(nodes[,1],nodes[,2])
nodes2<-nodes[-c(6,20,31),]
plot(nodes2[,1],nodes2[,2])  
weight=matrix(NA,33,33)
#nodes1<-read.table("nodes.txt")
for(i in 1:nrow(nodes2) ){ 
  for(j in 1:nrow(nodes2) ){ 
    weight[i,j]= sqrt((nodes2[i,1]-nodes2[j,1])^2+(nodes2[i,2]-nodes2[j,2])^2)
  }
}

weight1=round(weight,digit=0)
write.csv(nodes2, file="nodes2.csv")
write.csv(weight1, file="Int weight.csv")  


plot(sort(as.integer(weight)), pch=".")
points(sort(as.integer(weight[18,])))

#nodes2<-nodes[-c(6,20,31),]
N1[16,55]=0
N1[103,85]=0
N1[151,46]=0
sum(N1)

#divide the groups
kk=20
k=0
n1=sum(N1[1:(100+kk),1:(100+kk)])
nd1=matrix(NA,n1,2)
for(i in 1:(100+kk)){ 
  for(j in 1:(100+kk) ){ 
    if( N1[i,j]==1){
      k=k+1
      nd1[k,1]= i
      nd1[k,2]= j
    }
  }
}

#kk=20
k=0
n2=sum(N1[(101-kk):201,1:(100+kk)])
nd2=matrix(NA,n2,2)
for(i in (101-kk):201){ 
  for(j in 1:(100+kk) ){ 
    if( N1[i,j]==1){
      k=k+1
      nd2[k,1]= i
      nd2[k,2]= j
    }
  }
}

#kk=20
k=0
n3=sum(N1[1:(100+kk),(101-kk):210])
nd3=matrix(NA,n3,2)
for(i in 1:(100+kk)){ 
  for(j in (101-kk):210 ){ 
    if( N1[i,j]==1){
      k=k+1
      nd3[k,1]= i
      nd3[k,2]= j
    }
  }
}

#kk=20
k=0
n4=sum(N1[(101-kk):201,(101-kk):210])
nd4=matrix(NA,n4,2)
for(i in (101-kk):201){ 
  for(j in (101-kk):210 ){ 
              if( N1[i,j]==1){
                k=k+1
                nd4[k,1]= i
                nd4[k,2]= j
       }
    }
}



w1=matrix(NA,n1,n1)
w2=matrix(NA,n2,n2)
w3=matrix(NA,n3,n3)
w4=matrix(NA,n4,n4)
#nodes1<-read.table("nodes.txt")
for(i in 1:nrow(nd1) ){ 
  for(j in 1:nrow(nd1) ){ 
    w1[i,j]= sqrt((nd1[i,1]-nd1[j,1])^2+(nd1[i,2]-nd1[j,2])^2)
  }
}
for(i in 1:nrow(nd2) ){ 
  for(j in 1:nrow(nd2) ){ 
    w2[i,j]= sqrt((nd2[i,1]-nd2[j,1])^2+(nd2[i,2]-nd2[j,2])^2)
  }
}

for(i in 1:nrow(nd3) ){ 
  for(j in 1:nrow(nd3) ){ 
    w3[i,j]= sqrt((nd3[i,1]-nd3[j,1])^2+(nd3[i,2]-nd3[j,2])^2)
  }
}

for(i in 1:nrow(nd4) ){ 
  for(j in 1:nrow(nd4) ){ 
    w4[i,j]= sqrt((nd4[i,1]-nd4[j,1])^2+(nd4[i,2]-nd4[j,2])^2)
  }
}

write.csv(w1, file="w1.csv")  
write.csv(w2, file="w2.csv")  
write.csv(w3, file="w3.csv")  
write.csv(w4, file="w4.csv")  

write.csv(nd1, file="nd1.csv")  
write.csv(nd2, file="nd2.csv")  
write.csv(nd3, file="nd3.csv")  
write.csv(nd4, file="nd4.csv")  

library(vegan)
t1<-spantree(weight1)
plot(t1)

library(ape)
##mst output is the adjacency matrix of the minimum spaning tree
t2<-mst(weight1)
plot(t2)
#short2= function(x,y) { u = (x-y)/sqrt(sum((x-y)^2)); cbind( x-u*0.2,y+u*0.2)}
#short = function(x,y,a=0.2)  c(x + (y-x)*a, y+(x-y)*a)

plot(nodes2[,1],nodes2[,2]) 
lines(nodes2[1:2,1],nodes2[1:2,2])

#function to draw a mst with the iput as all the coordinates in mst
plot.mst=function(nd){
  weight=matrix(NA,nrow(nd),nrow(nd))
  for(i in 1:nrow(nd) ){ 
    for(j in 1:nrow(nd) ){ 
      weight[i,j]= sqrt((nodes2[i,1]-nodes2[j,1])^2+(nodes2[i,2]-nodes2[j,2])^2)
    }
  }
    weight1=round(weight,digit=0)
    require(ape)
    t2=mst(weight1)
    
    plot.mst1=function(t2,nodes2){
      # t2 is the minimum spanning tree matrix
      # nodes2 is the coordinates of all nodes (2 x n)
      pair=matrix(NA,2,sum(t2))
      k=0
      for(i in 1: nrow(t2)){
        for(j in i : ncol(t2)){
          if(t2[i,j]==1){ 
            k=k+1
            pair[1,(2*k-1)]=nodes2[i,1]
            pair[2,(2*k-1)]=nodes2[j,1]
            pair[1,(2*k)]=nodes2[i,2]
            pair[2,(2*k)]=nodes2[j,2]
          }
        }
      }
      plot(nodes2[,1],nodes2[,2], ylim=c(0,210), xlim=c(0,210)) 
      for( i in 1: k){
        lines(pair[,(2*i-1)],pair[,(2*i)])
      }
    }
    plot.mst1(t2, nodes2)
    
}


plot.mst1=function(t2,nodes2){
  # t2 is the minimum spanning tree matrix
  # nodes2 is the coordinates of all nodes (2 x n)
  pair=matrix(NA,2,sum(t2))
  k=0
  for(i in 1: nrow(t2)){
    for(j in i : ncol(t2)){
      if(t2[i,j]==1){ 
        k=k+1
        pair[1,(2*k-1)]=nodes2[i,1]
        pair[2,(2*k-1)]=nodes2[j,1]
        pair[1,(2*k)]=nodes2[i,2]
        pair[2,(2*k)]=nodes2[j,2]
        }
    }
  }
  plot(nodes2[,1],nodes2[,2], ylim=c(0,210), xlim=c(0,210)) 
  for( i in 1: k){
    lines(pair[,(2*i-1)],pair[,(2*i)])
    }
}
plot.mst1(t2, nodes2)

######## points mst
points.mst=function(t2,nodes2){
  # t2 is the minimum spanning tree matrix
  # nodes2 is the coordinates of all nodes (2 x n)
  pair=matrix(NA,2,sum(t2))
  k=0
  for(i in 1: nrow(t2)){
    for(j in i : ncol(t2)){
      if(t2[i,j]==1){ 
        k=k+1
        pair[1,(2*k-1)]=nodes2[i,1]
        pair[2,(2*k-1)]=nodes2[j,1]
        pair[1,(2*k)]=nodes2[i,2]
        pair[2,(2*k)]=nodes2[j,2]
      }
    }
  }
  points(nodes2[,1],nodes2[,2]) 
  for( i in 1: k){
    lines(pair[,(2*i-1)],pair[,(2*i)])
  }
}
##############################divide by four parts with 50% overlap
#just for drawing tree
tt1<-mst(w1)
tt2<-mst(w2)
tt3<-mst(w3)
tt4<-mst(w4)

plot.mst1(tt1,nd1)
points.mst(tt1,nd1)
points.mst(tt2,nd2)
points.mst(tt3,nd3)
points.mst(tt4,nd4)

#########################################Random forest/bootstrap
#random tree generation
sampling.nodes=function(k=10, n.tree=500,seed=1){
  set.seed(seed)
    # random sampling k iterm
sampling.nodes=matrix(NA,k,n.tree)
for(i in 1:n.tree){
  sampling.nodes[,i]=sample(nrow(nodes2), k )
}
sampling.nodes
}
sampling.nodes(10,10,1)


#####################################################
# mean of the sampled tree
average.tree=function(k=10,n.tree=10,seed=1, nodes2){
  sampling.nodes=function(k=10, n.tree=500,seed=1){
    set.seed(seed)
    # random sampling k iterm
    sampling.nodes=matrix(NA,k,n.tree)
    for(i in 1:n.tree){
      sampling.nodes[,i]=sample(nrow(nodes2), k )
    }
    sampling.nodes
  }
  sample1=sampling.nodes(k,n.tree,seed)
  
  sample.nd=array(NA,c(k,2,n.tree)) #sample nodes
  for (i in 1: n.tree){
    sample.nd[,,i]=nodes2[sample1[,i],]
  }
  
  sample.weight=array(NA,c(k,k,n.tree))#weight
  for(ii in 1:n.tree){
    for(i in 1:k){ 
      for(j in 1:k){ 
        sample.weight[i,j,ii]= sqrt((sample.nd[i,1,ii]-sample.nd[j,1,ii])^2
                                      +(sample.nd[i,2,ii]-sample.nd[j,2,ii])^2)
      }
    }
  }
  require(ape)# MST
  sample.tree=array(NA,c(k,k,n.tree))
  for(ii in 1: n.tree){
    sample.tree[,,ii]=mst(sample.weight[,,ii])
  }
  
  recover.tree=array(NA,c(nrow(nodes2),nrow(nodes2),n.tree))
  for(ii in 1:n.tree){
    for(i in 1:k){
      for(j in 1:k){
        if(sample.tree[i,j,ii]==1){ recover.tree[sample1[i,ii],sample1[j,ii],ii]=1
        }
      }
    }
  }
  recover.tree[is.na(recover.tree)] <- 0
  
  mean.tree=apply(recover.tree,1:2,sum)/n.tree
  mean.tree
  }


mt1<-average.tree(k=10,n.tree=100,seed=1, nodes2)


#################################################################

plot.mst2=function(t2,nodes2,cutoff=0.1, kk=1){
  # t2 is the minimum spanning tree matrix
  # nodes2 is the coordinates of all nodes (2 x n)
  pair=matrix(NA,2,sum(t2>0))
  k=0
  for(i in 1: nrow(t2)){
    for(j in i : ncol(t2)){
      if(t2[i,j]>cutoff){ 
        k=k+1
        pair[1,(2*k-1)]=nodes2[i,1]
        pair[2,(2*k-1)]=nodes2[j,1]
        pair[1,(2*k)]=nodes2[i,2]
        pair[2,(2*k)]=nodes2[j,2]
      }
    }
  }
  plot(nodes2[,1],nodes2[,2], ylim=c(0,210), xlim=c(0,210),main=kk+1) 
  for( i in 1: k){
    lines(pair[,(2*i-1)],pair[,(2*i)])
  }
}

mt1<-average.tree(k=17,n.tree=500,seed=1, nodes2)
plot.mst2(mt1,nodes2,0.1)

mt1<-average.tree(k=27,n.tree=500,seed=1, nodes2)
plot.mst2(mt1,nodes2,0.13)

k1<-c(5,33,165,500,1000,5000,50000)
mt=array(NA, c(33,33,length(k1)))
for(i in 1:length(k1)){
  mt[,,i]=average.tree(k=17,n.tree=k1[i],seed=1, nodes2)
}

diff1=NULL
diff2=NULL
diff3=NULL
for(i in 1:  (length(k1)-1)){
  diff1[i]=max(abs(mt[,, (i+1)]-mt[,,i]))
  diff2[i]=sum(abs(mt[,, (i+1)]-mt[,,i]))
  diff3[i]=sum((mt[,,(i+1)]-mt[,,i])^2)/(33*33)
} 
diff1;diff2;diff3
par(mfrow=c(2,2))
plot(log(k1),diff1, type="o",xlab="log(n.tree)",ylab="Max absolute differene",main="Probablity covergence")
plot(log(k1),diff2,type="o", xlab="log(n.tree)",ylab="Sum of absolute differene",main="Probablity covergence")
plot(log(k1),diff3, type="o", xlab="log(n.tree)",ylab="Variance",main="Probablity covergence")

# settle the n.tree=500
mt.nd<-array(NA, c(33,33,31))
for(i in 1:31){
  mt.nd[,,i]=average.tree(k=i+1,n.tree=5000,seed=1, nodes2)
}
pdf("nodes sampling number screen no cutoff.pdf", width=5, height=10)
par(mfrow=c(5,2))
for(i in 1:31){
  plot.mst2(mt.nd[,,i],nodes2, cutoff=0)
}
dev.off()

##################################################################
#3 measurements
# fault tolerence
# bridge functions
library(igraph)

num.bridge=function(t2){
  require(igraph)
  G<-graph.adjacency(t2,mode=c( "undirected"))
  num_comp <- length( decompose.graph(G) )
  k=0
  for (i in 1:length(E(G))) {
    G_sub <- delete.edges(G, i)
    if ( length( decompose.graph(G_sub) ) > num_comp ) 
    k=k+1
  }
  k
}  

num.bridge(mt.nd[,,31]>0.01)
#####################################cutoff screen
cutoff=NULL
c.position=NULL
for (i in 1:31){
  for(j in c(1:1089)[sort(mt.nd[,,i])>0]){
    if(num.bridge(mt.nd[,,i]>sort(mt.nd[,,i])[j])>0) {
      cutoff[i]=sort(mt.nd[,,i])[j-1]; c.position[i]=j-1;break}
  }
}

#################cutoff functions
#input probability matrix and num of bridges allowed
cutoff1=function(mt.nd,k ){
  cutoff=NULL
  c.position=NULL
  for (i in 1:dim(mt.nd)[3]){
    for(j in c(1:1089)[sort(mt.nd[,,i])>0]){
      if(num.bridge(mt.nd[,,i]>sort(mt.nd[,,i])[j])>k) {
        cutoff[i]=sort(mt.nd[,,i])[j-1]; c.position[i]=j-1;break}
    }
  }
  list(cutoff,c.position)
}
##the probablity distribution of the all the edges and the cutoff for a no bridge graph
pdf("the probablity distribution of the all the edges and the cutoff for a no bridge graph",width=5,height=10)
par(mfrow=c(5,2))
for(i in 1:31){
  plot(sort(mt.nd[,,i]), ylim=c(0,1), type="h")
  abline(v=c.position[i], col="red")
}
dev.off()

pdf(" cutoffed graph with on bridges",width=5,height=10)
par(mfrow=c(5,2))
for(i in 1:31){
  plot.mst2(mt.nd[,,i],nodes2, cutoff=cutoff[i])
}
dev.off()

pdf("total graph edge distri cutoff graph 5000",width=7.5,height=15)
par(mfrow=c(5,3))
for(i in 1:31){
  plot.mst2(mt.nd[,,i],nodes2, cutoff=0,kk=i)
  plot(sort(mt.nd[,,i]), ylim=c(0,1), type="h",main=i+1)
  abline(v=c.position[i], col="red")
  plot.mst2(mt.nd[,,i],nodes2, cutoff=cutoff[i],kk=i)
}
dev.off()
###########################################network evaluation
total.cost=NULL
m.dist=NULL
n.edge=NULL
w.mst=sum(weight1[t2>0])
for(i in 1:31){
  total.cost[i]=sum(weight1[mt.nd[,,i]>cutoff[i]])
  m.dist[i]=sum(weight1[mt.nd[,,i]>cutoff[i]])/sum(mt.nd[,,i]>cutoff[i])
  n.edge[i]=sum(mt.nd[,,i]>cutoff[i])
}
TL=total.cost/w.mst
pdf("performance evaluation of sampling number k.pdf", height=12, width=5)
par(mfrow=c(4,1))
plot(c(2:32),total.cost,xlab= "number of sampling nodes", ylab="Total Cost",type="o", main="Total Cost of Sampling Number Screen")
plot(c(2:32),TL,type="o", xlab= "number of sampling nodes",ylab="Normalized Total Cost",main="Normalized Total Cost of Sampling Number Screen")
plot(c(2:32),m.dist,type="o", xlab= "number of sampling nodes",main="Mean Distance of Sampling Number Screen")
plot(c(2:32),n.edge,type="o",xlab= "number of sampling nodes", main="Numbers of Edges of Sampling Number Screen")
dev.off()
w.mst=sum(weight1[t2>0])
########################################################################
plot.mst2(mt.nd[,,31],nodes2, cutoff=0.026)
#########################################################################
#fault tolerance measure for the each model 
FT <- vector("list", 31) #number of briges contained
TC <- vector("list", 31) #normalized cost 
TW <- vector("list", 31) #total weights of each graph
TE <- vector("list", 31) #total number of edges
w.mst=sum(weight1[t2>0])
for (i in 1:31){
  k=0
  for(j in c(1:1089)[sort(mt.nd[,,i])>0]){
    k=k+1
    G<-graph.adjacency(mt.nd[,,i]>sort(mt.nd[,,i])[j],mode=c( "undirected"))
      if(length( decompose.graph(G) )>1) break
   FT[[i]][k]=num.bridge(mt.nd[,,i]>sort(mt.nd[,,i])[j])
    TE[[i]][k]=sum(mt.nd[,,i]>sort(mt.nd[,,i])[j])/2
      TW[[i]][k]=sum(weight1[mt.nd[,,i]>sort(mt.nd[,,i])[j]])
     TC[[i]][k]=TW[[i]][k]/w.mst
      
     }
}

pdf("fault tolerance screen vs Cost 5000.pdf" ,height=15,width=5)  
par(mfrow=c(5,2))
for( i in 1:31){
  plot(TC[[i]], 1-FT[[i]]/TE[[i]],type="o", ylab="Fault Tolerance (FT)", xlab="Normalized Cost",main=i+1) 
}
dev.off()

pdf("fault tolerance screen vs Cost (clos up) 5000.pdf" ,height=15,width=5)  
par(mfrow=c(5,2))
for( i in 1:31){
  plot(TC[[i]], 1-FT[[i]]/TE[[i]],type="o", xlim=c(1,3), ylab="Fault Tolerance (FT)", xlab="Normalized Cost",main=i+1) 
}
dev.off()

#G<-graph.adjacency(mt.nd[,,i]>sort(mt.nd[,,i])[j],mode=c( "undirected"))

#weight2<-weight1
#weight2[t2<1]=0
#G<-graph.adjacency( weight2,mode=c( "undirected"),weighted=TRUE)
#get.all.shortest.paths(G, from=V(G), to = V(G) , mode = c( "all"),weights=weight2) 

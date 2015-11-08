
img <- readJPEG(system.file("img", "Rlogo.jpg", package="jpeg"))

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
write.csv(weight1, file="Int weight.csv")  


plot(sort(as.integer(weight)), pch=".")
points(sort(as.integer(weight[18,])))

nodes2<-nodes[-c(6,20,31),]
N1[16,55]=0
N1[103,85]=0
N1[151,46]=0
sum(N1)

#divide the Venn diagram like groups 
kk=50
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

kk=50
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

kk=50
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

kk=50
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
short2= function(x,y) { u = (x-y)/sqrt(sum((x-y)^2)); cbind( x-u*0.2,y+u*0.2)}
short = function(x,y,a=0.2)  c(x + (y-x)*a, y+(x-y)*a)

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
tt1<-mst(w1)
tt2<-mst(w2)
tt3<-mst(w3)
tt4<-mst(w4)

plot.mst1(tt1,nd1)
points.mst(tt2,nd2)
points.mst(tt3,nd3)
points.mst(tt4,nd4)

  

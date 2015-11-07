
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

  

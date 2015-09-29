#include<stdlib.h>
#include<iostream>
#include<math.h>

using namespace std;

class Point{
             private:
                        float x,y;
                        int clusterId;

                public:

                        Point(){
                                x = 0;
                                y = 0;
                                clusterId = 0;
                        }

                        Point(float x,float y){
                                this->x = x;
                                this->y = y;
                                clusterId = 0;
                        }

                        float getX(){
                                return x;
                        }

                        float getY(){
                                return y;
                        }

                        void setX(float x){
                                this->x = x;
                        }

                        void setY(float y){
                                this->y = y;
                        }

                        int getCluster(){
                                return clusterId;
                        }

                        void setCluster(int clusterId){
                                this->clusterId = clusterId;
                        }
        };

        float getDistance(Point p1,Point p2){
                return sqrtf(powf(p1.getX() - p2.getX(),2) + powf(p1.getY()-p2.getY(),2));
        }



        int main(){
            int NUM_DATA = 7, NUM_CLUSTERS = 2;

            Point centroids[NUM_CLUSTERS];

            Point dataSet[NUM_DATA];
	   bool noChange = false;

            dataSet[0].setX(1.0);
            dataSet[0].setY(1.0);
            dataSet[1].setX(1.5);
            dataSet[1].setY(2.0);
            dataSet[2].setX(3.0);
            dataSet[2].setY(4.0);
            dataSet[3].setX(5.0);
            dataSet[3].setY(7.0);
            dataSet[4].setX(3.5);
            dataSet[4].setY(5.0);
            dataSet[5].setX(4.5);
            dataSet[5].setY(5.0);
            dataSet[6].setX(3.5);
            dataSet[6].setY(4.5);
            

            centroids[0].setX(dataSet[0].getX());
            centroids[0].setY(dataSet[0].getY());
            centroids[0].setCluster(0);
            centroids[1].setX(dataSet[3].getX());
            centroids[1].setY(dataSet[3].getY());
            centroids[1].setCluster(1);

	cout<<"Centroids at :"<<endl;
                for(int i=0;i<NUM_CLUSTERS;i++){
                	cout<<centroids[i].getX()<<" "<<centroids[i].getY()<<endl;
                }

	    while(!noChange){
	    
	    Point oldCentroids[NUM_CLUSTERS];
	    
	    for(int i=0;i<NUM_CLUSTERS;i++){
	    	oldCentroids[i].setX(centroids[i].getX());
	    	oldCentroids[i].setY(centroids[i].getY());
	    	oldCentroids[i].setCluster(centroids[i].getCluster());
	    
	   // 	oldCentroids[i] = centroids[i];
	    }
	    
            for(int i=0;i<NUM_DATA;i++){
                int min = 10000;
                int clusterId;
                for(int j=0;j<NUM_CLUSTERS;j++){
                        float distance = getDistance(centroids[j],dataSet[i]);
                        if(distance < min){
                                min = distance;
                                clusterId = centroids[j].getCluster();
                        }
                        dataSet[i].setCluster(clusterId);
                }
            }

	    // recalculating centroids
	    
	    for(int i=0;i<NUM_CLUSTERS;i++){
	    	centroids[i].setX(0);
	    	centroids[i].setY(0);
	    }
	    
	    int *count;
	    
	    
	    
	    count = new int[NUM_CLUSTERS];
	    for(int i=0;i<NUM_DATA;i++){
	    	int currentCluster = dataSet[i].getCluster();
	    	
	    	centroids[currentCluster].setX(centroids[currentCluster].getX() + dataSet[i].getX());
	    	centroids[currentCluster].setY(centroids[currentCluster].getY() + dataSet[i].getY());
	    	count[currentCluster]++;
	    }
	    
	    for(int i=0;i<NUM_CLUSTERS;i++){
	    	centroids[i].setX(centroids[i].getX()/(float)count[i]);
	    	centroids[i].setY(centroids[i].getY()/(float)count[i]);
	    }
	    
	    delete count;
	    
	   // cout<<"Here"<<endl;
	   noChange  = true;  
	   for(int i=0;i<NUM_CLUSTERS;i++){
	   	if(centroids[i].getX() != oldCentroids[i].getX() || centroids[i].getY() != oldCentroids[i].getY()){
	   		noChange = false;
	   		break;
	   	}
	   }
	 }   

         //   cout<<getDistance(dataSet[0],dataSet[1]);
		cout<<endl;
		cout<<"-----------------------------------"<<endl;
		cout<<"X\t"<<"Y\t"<<"Cluster"<<endl;
		cout<<"-----------------------------------"<<endl;
                for(int i=0;i<NUM_DATA;i++){
                        cout<<dataSet[i].getX()<<"\t"<<dataSet[i].getY()<<"\t"<<dataSet[i].getCluster()<<endl;
                }
                cout<<endl;
                cout<<"Centroids at :"<<endl;
                for(int i=0;i<NUM_CLUSTERS;i++){
                	cout<<centroids[i].getX()<<" "<<centroids[i].getY()<<endl;
                }
           // cout<<p.getX()<<" "<<p.getY();
        }

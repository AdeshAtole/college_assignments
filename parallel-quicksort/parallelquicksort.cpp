//quicksort parallel
#include<iostream>
#include<algorithm>
#include<omp.h>
#include<stdlib.h>
#define N 1000000
using namespace std;

int MAX_THREADS;

void init(int *arr,unsigned int n){
	srand(102);
	for(unsigned int i=0;i<n;i++){
		arr[i] = rand()%1000;
	}
}


bool verify(int *arr,unsigned int n){
	for(unsigned int i=0;i<n-1;i++){
		if(arr[i]>arr[i+1]){
			return false;
		}
	}
	return true;
}

void quicksort(int *arr,int n){

	
	if(n>1){

		int pivot = 0;
		int i=1,j=n-1;
		while(i<j){
		


			while(arr[i]<arr[pivot] && i<n){
				i++;
			}

			while(arr[j]>=arr[pivot]){
				j--;
			}
			
			if(i>=j)
			{
				break;	
			}
			swap(arr[i],arr[j]);
		}
		
		
		if(j>0){
			swap(arr[pivot],arr[j]);
		}
		if(j<0)j=0;
		
		
		
		if(n>=(N/MAX_THREADS)){
			#pragma omp parallel sections
			{
				#pragma omp section
				quicksort(arr,j);
				#pragma omp section
				quicksort(&arr[j+1],n-j-1);
			}
		}else{
			
			quicksort(arr,j);
			quicksort(&arr[j+1],n-j-1);
		}
	}
}

int main(){
	int arr[N];
	init(arr,N);
	omp_set_nested(true);
	MAX_THREADS = omp_get_max_threads();
	int n = sizeof(arr)/sizeof(int);
	double t = omp_get_wtime();
	
	quicksort(arr,n);
	cout<<omp_get_wtime()-t<<endl;
	cout<<"N="<<n<<endl;
	cout<<verify(arr,N)<<endl;
/*	for(int i=0;i<n;i++){
		//cout<<"here"<<endl;
		cout<<arr[i]<<" ";
	}
*/	
	return 0;
	
}

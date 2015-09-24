//quicksort parallel
#include<iostream>
#include<algorithm>
#include<omp.h>
#include<stdlib.h>
#include<stdio.h>
#define N 1000000
using namespace std;

int MAX_THREADS;

/*
bool threadsLeft(){
	for(int i=0;i<MAX_THREADS;i++){
		if(threadStatus[i] == 0){
			return true;
		}
	}
	
	return false;
}*/

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
/*
	cout<<"Quicksort called\npivot="<<arr[0]<<endl;
	cout<<"n="<<n<<endl;
	cout<<"Array : ";
	for(int i=0;i<n;i++){
		cout<<arr[i]<<" ";
	}
	cout<<endl;
*/	
	
	if(n>1){

		int pivot = 0;
		int i=0,j=n-1;
		while(i<j){
		

			while(arr[i]<=arr[pivot] && i<(n-1)){
				i++;
			}

			while(arr[j]>arr[pivot] && j>0){
				j--;
			}
			
			if(i>=j)
			{
				break;	
			}
			swap(arr[i],arr[j]);
		}
		
		
			swap(arr[pivot],arr[j]);
		
/*		cout<<"Array after processing : ";
		
		for(int i=0;i<n;i++){
			cout<<arr[i]<<" ";
		}
		
		cout<<endl<<endl;
*/		
		if(j>=(N/(MAX_THREADS*2)) && (n-j-1)>=(N/(MAX_THREADS*2))){
			#pragma omp parallel sections  
			{
				#pragma omp section
				{
					quicksort(arr,j);
				}
				#pragma omp section
				{
					quicksort(&arr[j+1],n-j-1);
				}
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
	//cout<<"max threads"<<MAX_THREADS<<endl;
	
	int n = sizeof(arr)/sizeof(int);
	double t = omp_get_wtime();
	
	quicksort(arr,n);
	cout<<omp_get_wtime()-t<<endl;
	cout<<"N="<<n<<endl;
	cout<<verify(arr,N)<<endl;
	
/*	
	for(int i=0;i<n;i++){
		//cout<<"here"<<endl;
		cout<<arr[i]<<" ";
	}
*/	
	

	return 0;
	
}

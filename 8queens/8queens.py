import json

N=8
queens = [-1,-1,-1,-1,-1,-1,-1,-1]


def isValid(col,row):
	for i in range(col):
		if(queens[i] == row):
			return False
	
	
	for i in range(col):
		if(abs(col-i) == abs(row-queens[i])):
			return False
	
	
	return True
			

def place(n):
	placed = False;
	for i in range(N):
		if(isValid(n,i)):
			queens[n] = i
			placed=True
			break
			
	#print "here",queens,n
	
	if(not placed): 
	#	print "ret",n
		return False
		
	if( n == (N-1)):
	#	print "at final col"
		return True
		
	while(not place(n+1)):
		placed =  False
		for i in range(queens[n]+1,N):
			if(isValid(n,i)):
				queens[n] = i
				placed=True;
				break
		
		if(not 	placed):
			return False
			
	return True

inputFile = open("in")
data = json.loads(inputFile.read())

data=data["matrix"]

for i in range(0,N):
	if(data[i][0] == 1):
		queens[0] = i

#queens[0] = data[0][0]
place(1)
#print place(1)	
print queens
outfile = open("out","w")
data = {}
matrix = [[],[],[],[],[],[],[],[]]

for i in range(0,N) :
	for j in range(0,N):
		if(queens[j] == i):
			#matrix[i][j] = 1
			matrix[i].insert(j,1)
		else:
			#matrix[i][j] = 0
			matrix[i].insert(j,0)
			

data["matrix"] = matrix

#for i in range(N):
#	data[i] = queens[i]
json.dump(data,outfile)


public class Stack {
private int top;
private int[] elements;

Stack(int capacity){
	elements=new int[capacity];
	top=-1;
}

boolean isFull() {
	return(top+1==elements.length);	
}

boolean isEmpty() {
	return(top==-1);	
}

int size() {
	return top+1;
}

int peek() {
	if(isEmpty()) {
		System.out.println("Stack is empty");
		return 0;
	}
	else
		return elements[top];
}

void push(int data) {
	if (isFull())
		System.out.println("Stack Overflow");
	else {
		top++;
		elements[top]=data;
	}
}

int pop() {
	if(isEmpty()) {
		System.out.println("Stack is empty");
		return 0;
	}
	else {
		int retData=elements[top];
		top--;
		return retData;	
	}
}

boolean contains(int a) {
	boolean flag=false;
	for(int i=0;i<elements.length;i++) {
		if(elements[i]==a)
			flag=true;
	}
	return flag;
}



}

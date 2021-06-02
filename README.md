# AaDS
Project contains applications, algorithms and solutions to problems described below


# Projects
## List 1

### Task 1
Implement your own generic Array List (that behaves according to the [*ArrayList*](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/ArrayList.html) documentation) using java [*array*](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html).

The list should: 
1. Implement interface [*Iterable\<T\>*](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/lang/Iterable.html) according to the doccumentation ([\[1\]](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/Iterator.html), fail-fast behawior: [\[2\]](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/ArrayList.html#fail-fast)).  
	* Returned iterator must be created using anonymous class.  
	* No method should throw *UnsupportedOperationException*  
	
2. Implement methods:  
	* *add(int index, E element)*   
	* *add(E e)*  
	* *clear()*  
	* *constains(E o)*  
	* *ensureCapacity(int minCapacity)*  
	* *get(int index)*  
	* *indexOf(E o)*  
	* *set (int index, E element)*  
	* *remove(int index)*  
	* *size()*  
	
3. At least one method should use iterator.
4. Make us of [*@Override*](https://docs.oracle.com/javase/tutorial/java/IandI/override.html)
5. Take under the consideration [GRASP](https://en.wikipedia.org/wiki/GRASP_(object-oriented_design)) and [SOLID](https://en.wikipedia.org/wiki/SOLID)

## List 2

### Task 1
Implement your own sinking stack *MyStack\<T\>* implementing the interface *Istack\<T\>* and having the constructor *MyStack(int capacity)*.

### Task 2
Implement *Veloso's Traversable Stack* in class *VTStack\<T\>* which:
1. Will inherit the stack from the **Task 1** 
2. Implement methods:  
	* *T peek()*  
	* *void toTop()*  
	* *void down()*  
	
### Task 3
Use [TDD](https://en.wikipedia.org/wiki/Test-driven_development) when solving one of the tasks.

## List 3

### Task 1
Extend usage of list *OneWayLinkedListWithHead\<E\>* by implementing your own list:
1. Use composition instead of inheritance.
2. Implement method *listIterator()* implementing interface *ListIterator\<E\>* entirely based on [documentation](https://docs.oracle.com/javase/10/docs/api/java/util/ListIterator.html).
3. Methods *hasNext* and *hasPrevious* should be O(n).
 

## List 4

### Task 1
Using [devide & conquer](https://people.eecs.berkeley.edu/~vazirani/algorithms/chap2.pdf)(pages: 64 -66)  
Implement an algorithm that for given *ArrayList* wil return k-th smallest element from the list in O(n) time Complexity.

## List 5

### Task 1
Implement efficent sorting algorithms:
1. Bucket-Sort
2. Quick-Sort (using [magic fives algorithm](https://pl.wikipedia.org/wiki/Algorytm_magicznych_pi%C4%85tek))

Compare these algorithms on different data sets with each other and the *Collections.sort* and *Arrays.sort*. 

## List 7

### Task 1
Implement generic binary search tree and then create methods:
	* *insert(x)* 
	* *delete(x)*
	* *upper(x)* - returns *y* from the BST, such that *y* >= *x*, and *y* is the smallest such element
	* *lower(x)* - returns *y* from the BST, such that *y* <= *x* and *y* is the greatest such element.

Remember that methods *upper(x)* and *lower(x)* accept every parameter, including the ones not present in the BST.

### Task 2
Implement methods to traverse the structure from **Task 1**. The method must accept the arugment letting to choose traverse method:  
	* pre-order  
	* in-order  
	* post-order  

Additionally: Solve the task using [Visitor pattern](https://en.wikipedia.org/wiki/Visitor_pattern).

### Task 3
The tree from **Task 1** should be an AVL tree.

## List 8

### Task 1
Implement *generic binomial heap*. It should have methods:
	* *insert*
	* *minimum*
	* *extractMin*
	* *union*
	* *decreaseKey*
	* *delete*

## List 9

### Task 1
Implement *graph*. Choose the right implementation, think heavily about the pros and cons of the one you choose. Develop the proper interface.

### Task 2
Implement the algorithm of finding the *minimum spanning tree*. Include that into the interface.

### Task 3
Write and efficent algorithm of finding the shortest path. Take that under the consideration in interface. 

# Basic and external libraries

- openJDK-15,
- Jupyter: Junit 5.4.

# Author

- [Jakub Szwedowicz](https://github.com/JakubSzwedowicz)

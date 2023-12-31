import java.util.Scanner;

public class ArrayProject {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int newSize = 0;
		boolean error = false;
		
		do {
			try {
				System.out.println("--- ARRAY PROJECT ---");
				System.out.print("Enter array size: ");
				newSize = scan.nextInt();
				if (newSize > 0) {
					error = true;
				}
				else {
					System.out.println("Error: Invalid input. Please enter a positive integer.\n");
				}
			}
			catch (Exception e) {
				System.out.println("Error: Invalid input. Please enter an integer.\n");
				scan.next();
			}
		} while (!error);
		
		myArray newArray = new myArray(newSize);
		
		boolean exit = false;
		
		do {
			System.out.println("\nChoose an option: ");
			System.out.println("1. Add Element");
			System.out.println("2. View Element");
			System.out.println("3. Remove Element");
			System.out.println("4. Search");
			System.out.println("5. Sort");
			System.out.println("6. Edit");
			System.out.println("7. Exit");
			
			System.out.print("Enter option: ");
			
			try {
				int option = scan.nextInt();
				
				switch (option) {
				
				case 1: // ADD ELEMENT
					if (newArray.isFull()) {
						System.out.println("\nArray is full.");
						break;
					}
					else {
						System.out.println();
						for (int i = 0; i < newSize; i++) {
							System.out.print("Add Element: ");
							int element = scan.nextInt();
							newArray.addElement(element);				
						}
						break;
					}

				case 2: // VIEW ELEMENT
					if (newArray.isEmpty()) {
						System.out.println("\nArray is empty, please put an element first.");
					}
					
					System.out.print("\nArray: ");
					newArray.viewElement();
					break;
					
				case 3: // REMOVE ELEMENT
					boolean removeExit = false;
					
					if (newArray.isEmpty()) {
						System.out.println("\nArray is empty, please put an element first.");
						break;
					}

					do {
						System.out.print("\nCurrent Array: "); newArray.viewElement();
						
						System.out.print("\nEnter index to remove element: ");
						int index = scan.nextInt();
						
						if (index > newSize - 1 || index < 0) {
							System.out.println("Index " + index + " is not found or is out of range.");
							break;
						}
						
						newArray.removeElement(index);
						
						System.out.print("\nRemove another element (1 == YES || 0 == NO)? ");
						int removeOption = scan.nextInt();
						
						if (removeOption == 1) {
							removeExit = false;
						}
						else {
							removeExit = true;
						}
					} while (!removeExit);
					break;
					
				case 4: // SEARCH
					boolean searchExit = false;
					
					if (newArray.isEmpty()) {
						System.out.println("\nArray is empty, please put an element first.");
						break;
					}
					
					do {
						System.out.print("\nEnter value: ");
						int value = scan.nextInt();
						
						newArray.searchElement(value);
						
						System.out.print("\nSearch another value (1 == YES || 0 == NO)? ");
						int searchOption = scan.nextInt();
						
						if (searchOption == 1) {
							searchExit = false;
						}
						else {
							searchExit = true;
						}
					} while (!searchExit);
					break;
					
				case 5: // SORT (ASCENDING || DESCENDING)
					if (newArray.isEmpty()) {
						System.out.println("\nArray is empty, please put an element first.");
						break;
					}
					
					System.out.println("\nChoose sorting order:");
				    System.out.println("1. Ascending");
				    System.out.println("2. Descending");
				    System.out.print("Enter here: ");
				    int sortOption = scan.nextInt();
				    
				    boolean ascendingSort = (sortOption == 1);
				    
				    newArray.sortArray(ascendingSort);
				    
				    System.out.print("\nSorted Array: ");
				    newArray.viewElement();
				    break;
				    
				case 6: // EDIT
					boolean editExit = false;
					
					if (newArray.isEmpty()) {
						System.out.println("\nArray is empty, please put an element first.");
						break;
					}
					
					do {
						System.out.print("\nCurrent Array: "); newArray.viewElement();
						
						System.out.print("\nEnter an index to edit: ");
						int editIndex = scan.nextInt();
						
						if (editIndex > newSize - 1 || editIndex < 0) {
							System.out.println("Index " + editIndex + " is out of bounds.");
							break;
						}
						
						System.out.print("Enter value to be change: ");
						int editValue = scan.nextInt();
						
						newArray.editArray(editIndex, editValue);
						
						System.out.print("\nArray: ");
						newArray.viewElement();
						
						System.out.print("\nChange another value (1 == YES || 0 == NO)? ");
						int editOption = scan.nextInt();
						
						if (editOption == 1) {
							editExit = false;
						}
						else {
							editExit = true;
						}
					} while (!editExit);
					break;
					
				case 7: // EXIT
					System.out.println("\nGoodbye.");
					
					exit = true;
					break;
					
				default:
					System.out.println("Error: Invalid input. Please enter a valid option.");
					exit = false;
					break;
				}
			}
			catch (Exception e) {
				System.out.println("Error: Invalid input. Please enter a valid option.");
				scan.nextLine();
			}	
		} while (!exit);
		scan.close();
	}
}

class myArray {
	private int[] arr;
	private int size, index, count;
	
	public myArray (int _size) {
		size = _size;
		arr = new int[size];
		index = -1;
		count = 0;
	}
	
	public void addElement (int _value) { // ADDS ELEMENT TO THE ARRAY.
		if (count != size) { // IF COUNT IS NOT EQUAL SIZE THE USER STILL CAN ADD AN ELEMENT.
			arr[++index] = _value;
			count++;
		}
		else { // ELSE THIS WILL PRINT OUT.
			System.out.println("Array is full.");
		}
	}
	
	public void removeElement (int _index) { // REMOVES ELEMENT OF THE ARRAY.
		
		if (_index > size + 1) {
			System.out.println("Index out of bounds.");
		}
		
		int e = arr[_index];
		
		System.out.println("\nElement removed: " + "[" + e + "]");
		
		arr[_index] = 0;
		
		System.out.print("New Array: ");
		viewElement();
	}

	public void viewElement () { // VIEW THE ELEMENTS INSIDE OF THE ARRAY.
		String result = "[";
		
		for (int i = 0; i < count; i++) { // THIS LOOP GENERATES THE STRING VERSION OF THE ARRAY. 
	        result += arr[i];
	        if (i < count - 1) {
	            result += ", ";
	        }
	    }

	    result += "]";
	    
	    System.out.println(result);
	}
	
	public void searchElement (int _value) { // SEARCHES THE ELEMENT THAT IS ASKS BY THE USER.
		 boolean found = false;

		 for (int i = 0; i < arr.length; i++) {
			 if (arr[i] == _value) { // THIS WILL CHECK IF THE VALUE ENTERED BY THE USER IS EQUAL TO THE VALUE FOUND IN THE ARRAY.
				 System.out.println("Value " + _value + " is in index " + i);
				 
				 found = true;
				 break;
			 }
		 }

		 if (!found) { // IF THE USER ENTERED AN INVALID VALUE THIS WILL PRINT.
			 System.out.println(_value + " is not in the array.");
		 }
	}
	
	public void sortArray(boolean ascending) { // SORTS THE ARRAY IN ASCENDING AND DESCENDING ORDER USING THE BUBBLE SORT ALGORITHM.
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (ascending) { // SORTS THE ARRAY IN ASCENDING ORDER.
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                } 
                else { // SORTS THE ARRAY IN DESCENDING ORDER.
                	if (arr[j] < arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }
	
	public void editArray (int _index, int _value) { // EDITS THE VALUE OF AN ARRAY INDEX GIVEN BY THE USER.
		arr[_index] = _value;
	}
	
	public boolean isFull () {
		return count == size;
	}
	
	public boolean isEmpty () {
		return index < 0;
	}
}

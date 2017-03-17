package by.epam.l08.homework;


/*Реализуйте самостоятельно динамическую структуру Множество, не
содержащую дублирующихся элементов. Используйте параметризацию при
описании класса. (Условие не означает необходимости повторить все
возможности класса HashSet).*/

public class HW01hashset
{
	public static void main(String[] args)
	{
		Uset<String> mass = new Uset<String>();
		mass.add("Саша");
		mass.add("Маша");
		mass.add("Вика");
		mass.add("Юля");
		mass.add("Таня");
		mass.add("Аня");
		mass.add("Валя");
		mass.add("Лена");
		mass.add("Даша");
		mass.add("Вера");
		mass.add("Ксюша");
		mass.add("Настя");
		mass.add("Оля");
		mass.add("Люда");
		System.out.println("Размер:" + mass.memory() + ", используется:" + mass.getSize());

		mass.print();

		String s = "Ксюша";
		System.out.println("Наличие элемента \"" + s + "\": " + mass.contains(s));		

		mass.remove("Аня");
		mass.remove("Валя");
		mass.remove("Лена");
		mass.remove("Даша");
		
		mass.print();
		System.out.println("Размер:" + mass.memory() + ", используется:" + mass.getSize());
		
		mass.remove("Вера");
		mass.remove("Ксюша");
		mass.remove("Настя");
		mass.remove("Оля");
		mass.remove("Люда");
		
		mass.print();
		
		System.out.println("Размер:" + mass.memory() + ", используется:" + mass.getSize());
	}
}

class Uset<Type>
{
	private int size;// реальное кол-во элементов
	final int minsize = 10;// минимальный размер массива
	private final float extend_size = 1.1f;// автоматическое расширение массива
	private final float compact_size = 0.4f; // освобождение части массива
	private Object[] arr;

	public int getSize()
	{
		return size;
	}

	public Uset()
	{
		arr = new Object[minsize];
		size = 0;
	}

	private void resize()
	{
		int new_length = (int) Math.ceil(size * extend_size);
		Object[] arr_new = new Object[new_length];
		System.arraycopy(arr, 0, arr_new, 0, size);
		arr = arr_new;
		arr_new = null;
	}

	private void extend()
	{
		if (size == arr.length)// условие расширения массива
		{
			resize();
		}
	}

	private void compact()
	{
		int bound = (int) Math.ceil(arr.length * compact_size);
		if (size < bound)// условие сжатия массива
		{
			resize();
		}
	}

	public boolean add(Type element)
	{
		boolean result = false;
		if (contains(element) == false)
		{
			extend();
			arr[size] = element;
			size++;
		}
		return result;
	}

	public boolean remove(Type element)
	{
		boolean result = false;
		int index = find(element);
		if (index != -1)
		{
			System.arraycopy(arr, index + 1, arr, index, size - index);
			arr[size - 1] = null;
			size--;
			compact();
		}
		return result;
	}

	private int find(Type element)
	{
		int result = -1;
		for (int i = 0; i < size; i++)
		{
			if (arr[i].hashCode() == element.hashCode() && arr[i].equals(element))
			{
				result = i;
				break;
			}
		}
		return result;
	}

	public boolean contains(Type element)
	{
		boolean result = find(element) == -1 ? false : true;
		return result;
	}

	public Type[] toArray()
	{
		@SuppressWarnings("unchecked")
		Type[] result = (Type[]) new Object[size];

		System.arraycopy(arr, 0, result, 0, size);

		return result;

	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public void print()
	{
		for (int i = 0; i < size; i++)
		{
			System.out.print(arr[i].toString());
			System.out.print("; ");
		}
		System.out.println();
	}
	
	public int memory()
	{
		return arr.length;	
	}
};
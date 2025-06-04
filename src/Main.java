import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // todo: 1.	Фильтрация и сборка:
        //          Создайте список объектов Person с полями name, age и gender.
        //          Используя Stream, отфильтруйте только женщин старше 18 лет.
        //          Соберите их имена в новый список.

        Person personOne = new Person("Anna", 22, "Female");
        Person personTwo = new Person("Loan", 20, "Female");
        Person personTree = new Person("Tom", 21, "Male");
        Person personFour = new Person("Helen", 17, "Female");
        Person personFive = new Person("John", 19, "Male");

        List<Person> listTaskOne = new ArrayList<>();
        listTaskOne.add(personOne);
        listTaskOne.add(personTwo);
        listTaskOne.add(personTree);
        listTaskOne.add(personFour);
        listTaskOne.add(personFive);

        // Выполняем задание в два этапа:
        // 1. Сначала отфильтровываем всех старше 18 лет
        // 2. Затем отфильтровываем только женщин

        // Создаем поток данных из коллекции
        Stream<Person> streamOne = listTaskOne.stream();

        // Фильтруем данные потока по возрасту старше 18 лет
        Stream<Person> sortedByAgeStream = streamOne.filter(Person -> Person.getAge() > 18);

        // Собираем элементы отфильтрованного потока обратно в коллекцию
        List<Person> sortedByAgeList = sortedByAgeStream.toList();

        // Создаем новый поток из коллекции отфильтрованных по возрасту элементов
        Stream<Person> streamTwo = sortedByAgeList.stream();

        // Отфильтровываем элементы по женскому полу
        Stream<Person> sortedByGenderStream = streamTwo
                .filter(Person -> Objects.equals(Person.getGender(), "Female"));

        System.out.println("Task one:");
        sortedByGenderStream.forEach(System.out::println);


        // todo: 2.	Трансформация данных:
        //          Создайте список строк, представляющих числа в виде строк.
        //          Преобразуйте этот список в список целых чисел.
        //          Отфильтруйте только четные числа.
        //          Преобразуйте их в строковое представление.
        //          Соберите их в строку, разделенную запятыми.

        // Создаем поток с помощью статического метода класса Stream
        Stream<String> streamTaskTwo = Stream.of("23", "12", "64", "49", "92", "17", "78");

        // Преобразуем строки в числа и собираем элементы в коллекцию
        List<Integer> listInteger = streamTaskTwo.map(Integer::parseInt).toList();

        // Создаем новый поток из коллекции уже чисел
        Stream<Integer> streamTaskTwoSecond = listInteger.stream();

        // Отфильтровываем четные числа и собираем их в коллекцию
        List<Integer> listEvenInteger = streamTaskTwoSecond.filter(element -> element % 2 == 0).toList();

        // Преобразуем список целых чисел в строки опять
        List<String> listString = listEvenInteger.stream().map(String::valueOf).toList();

        // Выполняем конкатенацию строк
        String concatenated = listString.stream().reduce("", (a, b) -> a + b + ", ");

        System.out.println("Task two:");
        System.out.println(concatenated);


        // todo: 3.	Группировка и подсчет:
        //          Создайте список объектов Product с полями name, category и price.
        //          Используя Stream, сгруппируйте продукты по категории.
        //          Для каждой категории подсчитайте количество продуктов и их суммарную стоимость.

        Product productOne = new Product("Peach", "Fruits", 120);
        Product productTwo = new Product("Pork", "Meat", 350);
        Product productThree = new Product("Tea", "Drinks", 370);
        Product productFour = new Product("Plum", "Fruits", 150);
        Product productFive = new Product("Beef", "Meat", 520);
        Product productSix = new Product("Coffee", "Drinks", 410);
        Product productSeven = new Product("Mutton", "Meat", 680);
        Product productEight = new Product("Pear", "Fruits", 290);
        Product productNine = new Product("Lime", "Fruits", 130);

        List<Product> listTaskThree = new ArrayList<>();
        listTaskThree.add(productOne);
        listTaskThree.add(productTwo);
        listTaskThree.add(productThree);
        listTaskThree.add(productFour);
        listTaskThree.add(productFive);
        listTaskThree.add(productSix);
        listTaskThree.add(productSeven);
        listTaskThree.add(productEight);
        listTaskThree.add(productNine);

        // Создаем коллекцию продуктов категории Meat
        List<Product> listOfMeat = listTaskThree.stream()
                .filter(Product -> Objects.equals(Product.getCategory(), "Meat")).toList();

        // Создаем коллекцию продуктов категории Drinks
        List<Product> listOfDrinks = listTaskThree.stream()
                .filter(Product -> Objects.equals(Product.getCategory(),"Drinks")).toList();

        // Создаем коллекцию продуктов категории Fruits
        List<Product> listOfFruits = listTaskThree.stream()
                .filter(Product -> Objects.equals(Product.getCategory(), "Fruits")).toList();

        // Считаем количество типов продуктов в каждой категории
        // этот вариант: long numberOfFruits = listOfFruits.stream().count(); Idea заменила на следующий:
        long numberOfMeats = listOfMeat.size();
        long numberOfDrinks = listOfDrinks.size();
        long numberOfFruits = listOfFruits.size();

        System.out.println("Task three:");
        System.out.println("Types of meat are - " + numberOfMeats + "; " + "types of drinks are - "
                + numberOfDrinks + "; " + "types of fruits are - " + numberOfFruits);

        // Считаем суммарную стоимость всех продуктов каждой категории
        int sumOfMeat = listOfMeat.stream().mapToInt(Product::getPrice).sum();
        int sumOfDrinks = listOfDrinks.stream().mapToInt(Product::getPrice).sum();
        int sumOfFruits = listOfFruits.stream().mapToInt(Product::getPrice).sum();

        System.out.println("Meats cost - " + sumOfMeat + "; " + " Drinks cost - " + sumOfDrinks + "; "
                + " Fruits cost - " + sumOfFruits);


        // todo: 4.	Сортировка и поиск:
        //          Создайте список целых чисел.
        //          Отсортируйте его по убыванию.
        //          Найдите первые 3 элемента после сортировки.

        Stream<Integer> streamTaskFour = Stream.of(3, 21, 75, 36, 92, 5, 62, 19, 54, 81);

        // Сортируем список по убыванию
        Stream<Integer> dicrisingStream = streamTaskFour.sorted(Comparator.reverseOrder());

        System.out.println("Task four:");
        // Такой формат вывода на печать с добавлением пробела между элементами потока
    //    dicrisingStream.forEach(result -> System.out.print(result + " "));

        // Получаем первые три элемента отсортированного по убыванию списка
        List<Integer> firstThreeElements = dicrisingStream.limit(3).toList();
        System.out.println(firstThreeElements);


        // todo: 5.	Обработка данных из файла:
        //          Прочитайте содержимое текстового файла в список строк.
        //          Используя Stream, отфильтруйте только те строки, которые содержат определенное слово.
        //          Подсчитайте количество строк, соответствующих условию.


        // todo: 6.	Параллельная обработка:
        //          Создайте список чисел от 1 до 1000.
        //          Используя параллельный поток, найдите сумму квадратов всех чисел.
        //          Сравните время выполнения с обычным потоком.

        // Заполняем список тысячью значений от 1 до 1000
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 10000; i++) {
            int number = random.nextInt(10000);
            list.add(number);
        }

        // Замеряем время выполнение операции
        long start = System.currentTimeMillis();
        // Создаем новый список из квадратов всех значений списка list с помощью обычного потока
        List<Integer> firstModifiedList = list.stream().map(n -> n * n).toList();
        System.out.println("The time of a regular stream - " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        // Создаем новый список из квадратов всех значений списка list с помощью параллельного потока
        List<Integer> secondModifiedList = list.stream().parallel().map(n -> n * n).toList();
        System.out.println("The time of a parallel stream - " + (System.currentTimeMillis() - start));
    }
}
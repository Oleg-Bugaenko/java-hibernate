ДЗ 28. Hibernate

1. Створити базу даних Student із атрибутами: id, name, email.
2. Створити сутність Student та додати до неї інструкції. Поле id має заповнюватись автоматично. Ця сутність повинна відображати представлення таблиці Student з БД.
3. На вибір або
   Створити конфігураційний клас HibernateSession, в якому вказати властивості для підключення до БД та список класів з анотаціями Entity. У класі HibernateSession додати статичний метод отримання об'єкт типу SessionFactory.
   , Або Створити конфігураційний клас CustomEntityManagerFactory, в якому вказати властивості для підключення до БД та список класів з анотаціями Entity. У класі CustomEntityManagerFactory додати статичний метод отримання об'єкт типу EntityManager.
5. Створити DAO-клас для зберігання операцій: додавання, видалення, зміни сутності, отримання всіх записів та конкретної за ID.
6. Додати N-записів до таблиці Student.
7. Перевірити працездатність програми, виконати методи видалення, зміни, додавання записів, а також вибірки одного та всіх записів.


Результати роботи

1. Програма виконується в інтерактивному режимі шляхом вводу відповідних команд в консоль
----------------------------------------------------------------
-add       --> add a student and their email

-get-all   --> get data of all students

-get       --> obtaining student data by id

-remove    --> deleting student data by id

-change    --> changing student data

-exit      --> exit the program

-----------------------------------------------------------------
2. Для роботи програми необхідно виконати відповідні налаштування у файлі hibernate.cfg.xml
    - зокрема підключення до бази даних:
      - connection.url
      - connection.username
      - connection.password
3. Приклад виконання програми

   You are connected to the database of students' e-mail addresses
   use the following commands to work:
----------------------------------------------------------------
-add       --> add a student and their email

-get-all   --> get data of all students

-get       --> obtaining student data by id

-remove    --> deleting student data by id

-change    --> changing student data

-exit      --> exit the program

-----------------------------------------------------------------
-get-all

All students:

id=1, name= Alex, email= alex@gmail.com

id=2, name= Nick, email= nick@gmail.com

id=3, name= Mary, email= mary_N@gmail.com

----------------------
-add

Enter name: Lisa

Enter email: lisa@gmail.com

The record is created

----------------------
-remove

Enter id: 2

Record with 2 deleted successfully

----------------------

-get-all

All students:

id=1, name= Alex, email= alex@gmail.com

id=3, name= Mary, email= mary_N@gmail.com

id=4, name= Lisa, email= lisa@gmail.com

----------------------
-change

Enter id: 1

Enter name: Ivan

Enter email: ivan@gmail.com

----------------------
-get

Enter id: 1

id=1, name= Ivan, email= ivan@gmail.com

----------------------
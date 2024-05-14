## ЗАДАЧА ОБ ОБЕДАЮЩИХ ФИЛОСОФАХ!


### ПОСТАНОВКА ЗАДАЧИ

Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти. На столе между каждой парой ближайших философов лежит по одной вилке.

Каждый философ может либо есть, либо размышлять. Приём пищи не ограничен количеством оставшихся спагетти — подразумевается бесконечный запас. Тем не менее, философ может есть только тогда, когда держит две вилки — взятую справа и слева (альтернативная формулировка проблемы подразумевает миски с рисом и палочки для еды вместо тарелок со спагетти и вилок).

Каждый философ может взять ближайшую вилку (если она доступна) или положить — если он уже держит её. Взятие каждой вилки и возвращение её на стол являются раздельными действиями, которые должны выполняться одно за другим.

Вопрос задачи заключается в том, чтобы разработать модель поведения (параллельный алгоритм), при котором ни один из философов не будет голодать, то есть будет вечно чередовать приём пищи и размышления. 

*(Формулировка взята из Википедии).*



### СТРУКТУРА ЛАБАРАТОРНОЙ

Реализован вариант решения задачи с помощью *Семафора*, контролирующего доступ философов к ресурсу - вилкам. Здесь вилки представляются семафорами, блокирующими и разрешающими доступ к себе.

- В данной работе представлен класс **Philosopher**, наследуемого от класса Thread.
    - ___Поля___:
        - *name* - строчка-имя для определения конкретного философа;
        - *numberOfMeals* - численное количество приёмов пищи, необходимое для того, чтобы алгоритм когда-нибудь закончился;
        - *leftFork* - семафор, отвечающий за левую вилку;
        - *rightFork* - семафор, отвечающий за правую вилку;
    - ___Методы___:
        - *Philosopher(String _name, int _numberOfMeals, Semaphore _leftFork, Semaphore _rightFork)* - конструктор от заданных полей;
        - *doSomething(String action)* - общий метод для приёма пищи и размышлений, принимающий строчку(к примеру, "THINK" или "EAT");
        - *takeLeftFork()* - функция, отвечающая за взятие левой вилки, проверяющая её доступ;
        - *takeRightFork()* - функция, отвечающая за взятие правой вилки, проверяющая её доступ;
        - *putDownForks()* - функция, отвечающая за "отдачу" вилок, возвращение их на стол и открывающая её доступ;
        - *run()* - перезаписанный метод класса Thread (Поток), отвечающий за действия потока(в этом случае философа);
- В **Main** создаётся общий список *вилок* для философов - набор общих ресурсов и список *философов*.
    - Количество философов равно количеству вилок, поэтому можно поменять задачу на задачу об 3 или 6 обедающих философов.
    - Элементы списка вилок по индексам распределяются философам.
- Процесс запускается с помощью цикла, запуская метод *run()*.
- Добавлена весёлая иллюстрация, показываемая с помощью *javax.swing*


![Иллюстрация обедающих философов.](https://github.com/anekrasova210804/JavaLab2/raw/master/philosophers.png)


### ЗАКЛЮЧЕНИЕ

В итоге в консоли мы получаем расписанную последовательность действий философов до того момента, как они все поедят нужное количество раз, а также вывод иллюстрации.

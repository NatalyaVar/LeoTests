Учебный тест. Заходим на сайт lingualeo.com. Нужно вводить e-mail и пароль.

Класс DashBoardTest - на главной странице сайта проверяем меню в верхнем поле - нажимаем поочередно каждую кнопку, 
проверяем, что открылась (кроме главной) нужная страница, закрываем ее и возвращаемся на главную страницу.
Тест NotificationTest записывает содержимое списка из заметки в верхней части в текстовый файл output.txt и закрывет заметку. В этом тесте используется шаблон Page Object и записываются логи в файл C:\\logging.log - указывается в файле log4j.properties.
В тесте TrainingTest просто нажимаем кнопку и проверяем, нужная ли страница открылась.

Класс HeadTest - в тесте Head выбираем поля в центральной части шапки, заносим элементы в список, затем проходим по элементам списка - 
нажимем кнопку и проверяем правильность перехода на страницу, возвращаемся на главную страницу.
В тесте PopUp проверяем всплывающий список - перемещаем указатель  на поле с логотипом, чтобы появился всплывающий список,
затем текст из полей списка заносим в ArrayList, записываем этот список в файл output.txt.

Класс LeftBanner - при нажатии на поля в левой части страницы открывается еще одно окно. Проверяем, что открыто два окна, закрываем новое окно и возвращаемся на главную страницу. 

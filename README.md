# Мессенджер
## Обзор
Сервис является RESTfull API приложением.
Согласно ТЗ реализованы минимальные требования
### Сервис реализует упрощенный функционал мессенджера (минимальные требования)
#### Функционал для пользователя
* Регистрация нового пользователя
* Вход в систему
* Завершение текущей сессии
* Изменение информации профиля
* Изменение пароля
* Удаление аккаунта
* Отправка сообщений
* Просмотр истории сообщений с конкретным пользователем


### Использованные технологии
* Java 17
* Spring Boot
* Maven
* Lombok
* PostgreSQL


## Endpoints

### Регистрация нового пользователя
`POST /user` В случае размещения приложения на порте 8080 запрос будет выглядеть так:
**localhost:8080/createUser**

#### Параметры
`{
    "email":"yourEmail",
    "password": "yourPassword",
    "nickname": "yourNickname",
    "firstName": "yourFirstName",
    "secondName": "yourSecondName"
}` -
Принимает JSON с уникальными именем пользователя и почтовым адресом, иначе отказано в регистрации
#### Ответ
* В случае не уникальности email или nickname возвращает 'There is such email or nickname'
* В случае успешного запроса возвращает профиль пользователя и переводит его статус на "В сети":
`{
"id": 10,
"email": "yourEmail111",
"password": "yourPassword",
"nickname": "yourNickname111",
"firstName": "yourFirstName",
"secondName": "yourSecondName",
"active": true,
"deleted": false
}`

#### Пример запроса
![image](https://github.com/SW0301/messenger/assets/104074337/8895c384-b346-4369-b308-9bf8ca395e78)
#### Пример ответа
![image](https://github.com/SW0301/messenger/assets/104074337/bf982713-2dbe-4dae-93b8-1bbfc2b634d6)
##### Пример ошибки регистрации
![image](https://github.com/SW0301/messenger/assets/104074337/85a76359-8e75-4760-9759-25982a8ed361)

### Вход в систему
`PUT /user/login` В случае размещения приложения на порте 8080 запрос будет выглядеть так:
**localhost:8080/checkBalance**
#### Параметры
`{
"emailOrNickname": "yourEmail111",
"password": "yourPassword"
}` -
Принимает email или nickname и пароль пользователя
#### Ответ
    
* Возвращает сообщение в случае успешного входа `Welcome!` 
  
* В случае если пользователь уже зашел в систему ранее возвращает сообщение `You are already logged in`

* В случае неверного логина возвращает сообщение `Account is deleted or does not exists`

* В случае неверного пароля возврашает сообщение `Wrong password`
* 
#### Пример запроса
![image](https://github.com/SW0301/messenger/assets/104074337/235c3a8e-dc7a-4828-83b3-27eff7edd0f2)
#### Пример ответа
![image](https://github.com/SW0301/messenger/assets/104074337/10ab719f-b43a-4fd1-bb6b-f6e65008d579)
##### Примеры ошибки входа
![image](https://github.com/SW0301/messenger/assets/104074337/ec2abb84-28c8-4681-a29a-e1ab59ff7a4d)
![image](https://github.com/SW0301/messenger/assets/104074337/14794c53-6fee-4416-9f93-841f083e82f0)
![image](https://github.com/SW0301/messenger/assets/104074337/138713ce-255c-4f81-957c-831467cc04c5)

### Завершение текущей сессии
`PUT /user/logout/{id}` В случае размещения приложения на порте 8080 запрос будет выглядеть так:\
**localhost:8080/addBalance/{id}**
#### Параметры

Принимает id пользователя в запросе
#### Ответ

* Возвращает сообщение  `See you` в случае успешного выхода из системы
* Возвращает сообщение `Something wrong` в случае не правильного вводы id
* 
#### Пример запроса
![image](https://github.com/SW0301/messenger/assets/104074337/110aef37-d429-4d4f-9592-485ca881f5ac)
#### Пример ответа
![image](https://github.com/SW0301/messenger/assets/104074337/039f84ae-90c8-4a79-b0c2-937f575e06a4)
##### Примеры ошибки выхода
![image](https://github.com/SW0301/messenger/assets/104074337/4a00fdec-46c3-498a-9c56-d9f9ae3b89b9)

### Изменение информации профиля
`PUT /user/{id}` В случае размещения приложения на порте 8080 запрос будет выглядеть так:\
**localhost:8080/user/{id}**
#### Параметры
`{
"email": "yourEmail11111",
"nickname": "yourNickname11134",
"firstName": "yourFirstName",
"secondName": "yourSecondName"
}` -
Принимает id пользователя в запросе \
В теле запроса принимает парамсетры email, nickname, firstName, secondName
#### Ответ
`{
"id": 3,
"email": "yourEmail11111",
"password": "1235",
"nickname": "yourNickname11134",
"firstName": "yourFirstName",
"secondName": "yourSecondName",
"active": true,
"deleted": false
}` -
Возвращает обновленный профиль пользователя \
В случае если пользователь не в системе, возвращает сообщение `Войдите в систему` \
В случае не уникальности нового email или nickname возвращает сообщение `There is the same email or nickname` 


#### Пример запроса
![image](https://github.com/SW0301/messenger/assets/104074337/6b002ab2-2750-4d90-bc1b-e4ea630e98b4)
#### Пример ответа
![image](https://github.com/SW0301/messenger/assets/104074337/c0c4b4bc-881a-48f0-893c-fa4b2b17faf0)
##### Примеры ошибки изменения профиля
![image](https://github.com/SW0301/messenger/assets/104074337/1ba6f574-8768-4ff4-a30a-12a2358c0de6)
![image](https://github.com/SW0301/messenger/assets/104074337/adb4cea5-ad15-4f4c-bd7c-cd665e691e49)

### Изменение пароля
`POST /user/password/{id}` В случае размещения приложения на порте 8080 запрос будет выглядеть так:
**localhost:8080/user/password/{id}**
#### Параметры

Принимает id пользователя в пути запроса \
В параметре запроса принимает password 
#### Ответ
`{
"id": 3,
"email": "yourEmail11111",
"password": "1235",
"nickname": "yourNickname11134",
"firstName": "yourFirstName",
"secondName": "yourSecondName",
"active": true,
"deleted": false
}` -
Возвращает обновленный профиль пользователя \
В случае если пользователь не вошел в систему выводится сообщение `Войдите в систeму` \
#### Пример запроса
![image](https://github.com/SW0301/messenger/assets/104074337/399b0fab-73d8-4801-815b-7909596bae33)
#### Пример ответа
![image](https://github.com/SW0301/messenger/assets/104074337/c0c4b4bc-881a-48f0-893c-fa4b2b17faf0)
##### Пример ошибки изменения пароля
![image](https://github.com/SW0301/messenger/assets/104074337/fb0e2c0b-0abe-46ac-83a7-e9b1cdec1981)


### Удаление аккаунта
`DEL /user/{id}` В случае размещения приложения на порте 8080 запрос будет выглядеть так:\
**localhost:8080/user/{id}**
#### Параметры
Принимает id пользователя в пути запроса
#### Ответ

Возвращает сообщение `Bye` в случае успешного удаления \
Если пользователь не вошел в систему, то выводит сообщение `Войдите в систему` \
Если аккаунт не найден или уже был удален, то выводит сообщение `Something wrong`

#### *Стоит отметить что пользователь не удаляется на совсем из базы данных и можно добавить функционал позволяющий восстановить аккаунт*

#### Пример запроса
![image](https://github.com/SW0301/messenger/assets/104074337/21d5f869-5298-48c6-a7ac-3522e8804787)
#### Пример ответа
![image](https://github.com/SW0301/messenger/assets/104074337/500431f1-3a56-4966-91f6-82318f95cf43)
##### Примеры ошибки удаления аккаунта
![image](https://github.com/SW0301/messenger/assets/104074337/6cd0cb05-18bf-41cb-94f5-a0d450bc9ad9)
![image](https://github.com/SW0301/messenger/assets/104074337/f9fdb060-1856-4d03-af47-d27f3a647422)


### Отправка сообщений
`POST /message/{id}` В случае размещения приложения на порте 8080 запрос будет выглядеть так:\
**localhost:8080/message/{id}**
#### Параметры
`{
"text":"12441243423",
"toUserId": 1
}` -
Принимает id пользователя в пути запроса\
В теле запроса принимает текст сообщения и id пользователя которому хочет отправить сообщение

#### Ответ

Возвращает сообщение в случае успешной отправки сообщения `The message has been sent` \
В случае если пользователь не зашел в систему выводит сообщение `Войдите в систему` \
В случае если пользователь для отправки сообщения не найден выводит сообщение `Can not find user with this id`

#### Пример запроса
![image](https://github.com/SW0301/messenger/assets/104074337/79cdf618-f100-474e-bac8-5c87a2f0a752)
#### Пример ответа
![image](https://github.com/SW0301/messenger/assets/104074337/7576a3ba-0232-47fd-80b1-3069f79c5be1)
##### Примеры ошибки отправки сообщения
![image](https://github.com/SW0301/messenger/assets/104074337/adfeac3e-55e2-4169-9ba1-74560a25ba0f)
![image](https://github.com/SW0301/messenger/assets/104074337/a55a5148-26a8-4001-aa05-34f820efc471)




### Просмотр истории сообщений с конкретным пользователем
`GET /message/{id}` В случае размещения приложения на порте 8080 запрос будет выглядеть так:\
**localhost:8080/message/{id}**
#### Параметры
Принимает в пути запроса id пользователя который отправляет запрос, а так же в параметре запроса принимает 
параметр `toUserid` - историю сообщений с которым хочет посмотреть пользователь
#### Ответ
`[
{
"from": "yourNickname3",
"text": "qwfdafge"
},
{
"from": "yourNickname3",
"text": "123"
},
{
"from": "yourNickname3",
"text": "123423423"
},
{
"from": "yourNickname11134",
"text": "124423"
},
{
"from": "yourNickname11134",
"text": "12441243423"
},
{
"from": "yourNickname11134",
"text": "12441243423"
},
{
"from": "yourNickname11134",
"text": "12441243423"
}
]` \
Вовращает полную историю сообщений с пользователем в случае успешного запроса \
Если пользователь не зашел в систему и хочет посмотреть историю сообщений то выводится сообщение `Войдите в систему` \
Если не найден аккуант с которым пользователь хочет посмотреть историю сообщений, то выводится сообщение `Can not find user with what id`

#### Пример запроса
![image](https://github.com/SW0301/messenger/assets/104074337/254100c7-3bb1-4cdc-babb-b6c8798051a2)
#### Пример ответа
![image](https://github.com/SW0301/messenger/assets/104074337/a1fde41f-5e2a-4ac2-8d69-e0ffebeb9487)
##### Примеры ошибки отправки сообщения
![image](https://github.com/SW0301/messenger/assets/104074337/adfeac3e-55e2-4169-9ba1-74560a25ba0f)
![image](https://github.com/SW0301/messenger/assets/104074337/a2e41aef-6c88-496e-88aa-33b10ff085d9)

## Можно добавить
* При входе в систему возвращение токена, который будет храниться у пользователя и использоваться при выходе
из системы, изменении информации профиля, изменении пароля, удалении аккаунта, отправке сообщений и просмотра 
истории сообщений
* Подтверждение почты пользователя
* Возможность восстановить удаленный аккаунт
# Contribute - Wishlist Project

### Introduction
The purpose of the project is to enhance your skills within some of the subject elements that are included in the exam project. The mini-project provides you with the opportunity to work problem-based and interdisciplinary with requirements, design, and implementation of a web-based database application.

<br> 

## ER-Diagram Wishlist project

<br>

```mermaid
    erDiagram
        NEW-USER ||--o| USERS : creates-account

        USERS ||--o{ WISHLIST : user-create-wish
        
        USERS {
            int user_id PK
            String first_name
            String last_name
            String email
            String password
            int wish_id FK
        }

        WISHLIST {
            int wish_id PK
            String wish_titel
            String wish_description
            String wish_url
            Blob wish_picture
            int user_id FK
        }
```

<br>
<br>

## Tables
In our project we have 2 tables, that we are working with. A table that includes all information about each individual wish and one that holds all user information. In the snippet below, you can see how the tables have been set up in MySQL.
```
CREATE TABLE users
(
    user_id       INTEGER NOT NULL AUTO_INCREMENT,
    first_name    VARCHAR(20),
    last_name     VARCHAR(20),
    user_email    VARCHAR(50) UNIQUE,
    user_password VARCHAR(50),
    PRIMARY KEY (user_id)
);

CREATE TABLE wishlist
(
    wish_id          INTEGER NOT NULL AUTO_INCREMENT,
    wish_title       VARCHAR(50),
    wish_description VARCHAR(255),
    wish_url         VARCHAR(2083),
    wish_picture     BLOB,
    user_id          INTEGER,
    PRIMARY KEY (wish_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
```

As you can see, 'users' contains all the user information, that is asked to fill out when you create an account. Our primary key is set us as being the user_id, which we uses frequently to check their login and as a way to navigate the page. Also it is setup to never be null, meaning if nothing is set on the value, it will just add +1 to the latest value in the table.

The 'wishlist' table is set up with all the information needed about the wish. Wishes are also set up to have an id, in the same way users do. This value is also set to never be null. Other than that, as you can see 'wishlist' includes a title, description, url and a picture, which we did not put that much use for unfortunatly. In this table, 'wish_id' is our primary key. The 'wishlist' table also includes user_id, and is referenced to be the same as whatever it is in the 'users' table.

### Classes and Setup

Sign-up:
When signing up it's requirede to enter; first name, last name, email and password.

These information will be taken by the class **wishlistController** where createUser methode will create a new instance of a user and send it to the html-page "create-user"

```java
    @GetMapping("/create")
    public String createUser(Model model){
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "create-user";
    }
```

After the instance has been filled with the users informations the methode:

```java
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute User newUser, Model model){
        int userId = wishlistRepository.createUser(newUser);
        return "created-user";
    }
```

## Class Diagram Wishlist project
<br>


<br>

```mermaid
    classDiagram
        WishlistController "1..*" o--> "1" WishlistRepository
        LoginRepository "1" <--o "1" LoginController
        WishlistController "1" o--> "1" LoginController
        Wish "1" <.. "0..*" User
        User "1..*" <.. "1" WishlistRepository
        Wish "1..*"..> WishlistRepository
        WishlistController "1" ..> "0..*" Wish
        LoginRepository .. WishlistRepository
        
        
        

        class User{
            +userId: int
            +firstName: String
            +lastName: String
            +email: String
            +password: String

            +getUserId()
            +getFirstName()
            +getLastName()
            +getEmail()
            +getPassword()

            +setFirstName()
            +setLastName()
            +setEmail()
            +setPassword()
        }

        class Wish{
            +wishId: int
            +titel: String
            +description: String
            +url: String
            +userId: int

            +getWishId()
            +getTitle()
            +getDescription()
            +getUrl()
            +getUserId()

            +setTitle()
            +setDescription()
            +setUrl()
        }

        class WishlistController{
            +repository: WishlistRepository

            +landingPage()
            +createUser()
            +addUser()
            +mainPage()
            +createWish()
            +addWish()
            +updateWish()
            +updateUserWish()
            +deleteWish()
        }

        class WishlistRepository{
            +url: String
            +user_id: String
            +user_pwd: String

            +createUser()
            +getUser()
            +createWish()
            +getWishlist()
            +getSpecificWish()
            +updateWish()
            +deletetWish()
        }

        
           class LoginController{
            +repository: LoginRepository
            +current_user: int

            +isLoggedIn()
            +landingPage()
            +showLogin()
            +login()
            +logout()
        }

         class LoginRepository{
            +url: String
            +user_id: String
            +user_pwd: String

            +checkEmail()
        }

      

``` 

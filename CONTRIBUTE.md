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

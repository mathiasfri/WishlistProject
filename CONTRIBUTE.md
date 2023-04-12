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

## Class Diagram Wishlist project

<br>

```mermaid
    classDiagram
        WishlistController --|> WishlistRepository
        WishlistController <|-- WishlistRepository
        Wish <|--User
        
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


``` 

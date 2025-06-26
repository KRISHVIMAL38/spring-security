# spring-security

//Register Normal User
http://localhost:9393/auth/registernormaluser
{
    "id": 1,
    "username": "Richard",
    "email": "Richard@gmail.com",
    "password": "$2a$10$f443lsKu3FkSJ0Qe8VVsbebeeqfkHXwibv4vjY51rudWcqt3c.6Hy",
    "roles": [
        "ROLE_USER"
    ],
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "enabled": true,
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "accountNonLocked": true
}


//Login Normal User
http://localhost:9393/auth/login
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSaWNoYXJkIiwiaWF0IjoxNzUwNTc1ODk3LCJleHAiOjE3NTA2NjIyOTd9.LpMEVtjFlILcrxrxGvAtgpVc99hQWQeipbIc4zZLZ_o",
    "userName": "Richard",
    "roles": [
        "ROLE_USER"
    ]
}

Adding as a first admin
insert into users values(2,"Krishna@1234","$2y$10$4HAopVA/mOdyxqEDyIuSHuy84D3g0zy91JxMV41X/bnIKqX2DrDL.","Krishna");

insert into user_roles values(2,"ROLE_USER");

insert into user_roles values(2,"ROLE_ADMIN");

//Login First Admin
http://localhost:9393/auth/login

{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJLcmlzaG5hIiwiaWF0IjoxNzUwNTc2MTU2LCJleHAiOjE3NTA2NjI1NTZ9.ZMjqtjfIEV4qADh4mNCV-h8sooh_VlslcuLU8UL9M4I",
    "userName": "Krishna",
    "roles": [
        "ROLE_USER",
        "ROLE_ADMIN"
    ]
}

Register other admin as a admin
http://localhost:9393/admin/registeradminuser

{
    "id": 3,
    "username": "Shivam",
    "email": "Shivam@gmail.com",
    "password": "$2a$10$0aozZxs5fKukdbAY6bkqIubjmWClM3gYj1kMv7bNYS2nTu/SsM5yO",
    "roles": [
        "ROLE_USER",
        "ROLE_ADMIN"
    ],
    "authorities": [
        {
            "authority": "ROLE_USER"
        },
        {
            "authority": "ROLE_ADMIN"
        }
    ],
    "enabled": true,
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "accountNonLocked": true
}

Adding the Book (Must be he admin)
 http://localhost:9393/books
{
    "id": 1,
    "titile": "RamCharitManas",
    "isbn": "ABC-979-jkhj",
    "quantity": 3,
    "available": false,
    "author": null
}

Get All Books 
http://localhost:9393/books
[
    {
        "id": 2,
        "titile": "Kaluhanuvani",
        "author": "Unknown",
        "isbn": "ABC-979-jkhj",
        "quantity": 3,
        "available": false
    },
    {
        "id": 4,
        "titile": "Hanuman Chalisa",
        "author": "Tulsidas",
        "isbn": "ABC-979-jkhj",
        "quantity": 100,
        "available": true
    },
    {
        "id": 5,
        "titile": "System Design",
        "author": "Durgesh Tiwari",
        "isbn": "ABC-000-jkhj",
        "quantity": 5,
        "available": false
    }
]

GetBookById
http://localhost:9393/books/1
{
    "id": 1,
    "titile": "RamCharitManas",
    "isbn": "ABC-979-jkhj",
    "quantity": 3,
    "available": false,
    "author": null
}

Issue The record
http://localhost:9393/issuerecords/issuethebook/4

{
    "id": 1,
    "issueDate": "2025-06-25",
    "dueDate": "2025-07-09",
    "returnDate": null,
    "isReturned": false,
    "user": {
        "id": 1,
        "username": "Richard",
        "email": "Richard@gmail.com",
        "password": "$2a$10$f443lsKu3FkSJ0Qe8VVsbebeeqfkHXwibv4vjY51rudWcqt3c.6Hy",
        "roles": [
            "ROLE_USER"
        ],
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ],
        "enabled": true,
        "accountNonExpired": true,
        "credentialsNonExpired": true,
        "accountNonLocked": true
    },
    "book": {
        "id": 4,
        "titile": "Hanuman Chalisa",
        "author": "Tulsidas",
        "isbn": "ABC-979-jkhj",
        "quantity": 99,
        "available": true
    }
}



return the book;
http://localhost:9393/issuerecords/returnthebook/1

{
    "id": 1,
    "issueDate": "2025-06-25",
    "dueDate": "2025-07-09",
    "returnDate": "2025-06-25",
    "isReturned": true,
    "user": {
        "id": 1,
        "username": "Richard",
        "email": "Richard@gmail.com",
        "password": "$2a$10$f443lsKu3FkSJ0Qe8VVsbebeeqfkHXwibv4vjY51rudWcqt3c.6Hy",
        "roles": [
            "ROLE_USER"
        ],
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ],
        "enabled": true,
        "accountNonExpired": true,
        "credentialsNonExpired": true,
        "accountNonLocked": true
    },
    "book": {
        "id": 4,
        "titile": "Hanuman Chalisa",
        "author": "Tulsidas",
        "isbn": "ABC-979-jkhj",
        "quantity": 100,
        "available": true
    }
}

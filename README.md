# Booked-API

# Description
- This is a RESTful API designed for this project: [Booked](https://www.figma.com/community/file/1266829619368231584)


- The corresponding frontend can be found here: [Booked-Frontend](https://github.com/telmopanacas/Booked-Website)

# Change Log
Guide for writing change logs.

`Added` for new features.

`Changed` for changes in existing functionality.

`Deprecated` for soon-to-be removed features.

`Removed` for now removed features.

`Fixed`  for any bug fixes.

## 2023-09-20

### Added
- Added a custom exception `ApiRequestException` to be used throughout the application.


- Added the class `ApiException` to be used as a response in case of an exception.


- Added the class `ApiExceptionHandler` to handle the exceptions thrown by the application.


- Added the query `findByDisplayName` to the `UserRepository`.


- In the function `register` from the `AuthenticationService` added the verification of the user's display name to see if it's already being used.


- In the function `authenticate` from the `AuthenticationService` added a `try catch` block to catch the `BadCredentialsException` and throw a `ApiRequestException` instead.

### Changed
- In the `AuthenticationService` the function `register` now checks if a user already exists with the same email before creating a new one.


- In the `AuthenticationService, AvaliacaoService, ComentarioService, LivroService, UserService` implemented the `ApiRequestException` instead of normal `Exceptions`.

### Deprecated
- N/A.


### Removed
- N/A.


### Fixed
- N/A.


## 2023-09-18

### Added
- Added the record `UserDTO` to be used in the new `UserController`.


- Added the `UserDTOMapper` to map the `User` model to the `UserDTO` record.


- Added the `UserRequest` class to be used in the `UserController` to receive the user data from the frontend.


- Added the `UserController` with the following endpoints:
    - `POST` `api/v1/user` - Which receives a `UserRequest` and returns the user corresponding to the request's email.


- Added the `UserService` with the following functions:
    - `getUserByEmail` - Which receives a `String` email and returns the user corresponding to the email.

### Changed
- In the `Avaliacao` entity removed the `autor` property and added the `userId` property with a `ManyToOne` relation.


- In the `User` entity added the `avaliacoes` property and created a `OneToMany` relation between `User` and `Avaliacao`.


- In the `AvaliacaoDTOMapper` changed the `autor` property to `displayName` of it's `user` property.
### Deprecated
- For now commented the unit tests related with the `Avaliacao` entity.

### Removed
- Removed the query `findByAuthor` from the `AvaliacaoRepository`.

### Fixed
- N/A.

## 2023-09-13

### Added
- N/A.


### Changed
- In the `AuthenticationController` and the `AuthenticationService` added the `refreshToken` to be set as a cookie in the response.


### Deprecated
- N/A.


### Removed
- N/A.


### Fixed
- N/A.


## 2023-09-08

### Added
- N/A.

### Changed
- Changed the property `spring.jpa.show-sql` to `false` in the `application.properties` file.


- Changed the property `refreshToken` in the `AuthenticationResponse` to be ignored by the JSON parser.


- In the `AuthenticationController` changed the `refreshToken` to be set as a cookie in the authentication endpoint only.


- In the `AuthenticationController` added the endpoint `api/v1/auth/logout` which deletes the refresh token cookie.


- in the `AuthenticationService` changed the `authenticate` function to set the `refreshToken` as a cookie in the response.


- in the `SecurityConfiguration` added a `CorsConfigurationSource` bean to allow CORS requests from the frontend.

### Deprecated
- N/A.

### Removed
- Removed the `WebMvcConfigurer` bean in the `BookedApiApplication` class.

### Fixed
- N/A.

## 2023-08-29

### Added
- N/A.

### Changed
- Changed the endpoint `api/v1/livro/new` so after the book is created it returns it in the response.


- Changed the relation between `Livro` and `Avaliacao` so that when a book is deleted all its reviews are deleted as well.


### Deprecated
- N/A.

### Removed
- N/A.

### Fixed
- N/A.


## 2023-08-25

### Added
- Added the endpoint `api/v1/livro/find` which receives the parameters `titulo` and `autor` and the book that match the parameters. 


- Added the corresponding function to the `Livro` service.


- Added the unit test for the `Livro` service.
### Changed
- Changed the `Livro` Entity where the property ISBN was removed since there was no need for it yet.


- Changed the `LivroService` functions to reflect the change in the Livro Entity.


- Changed the `db_init.sql` file to reflect the change in the Livro Entity.


- Changed the tests to reflect the change in the Livro Entity.

### Deprecated
- N/A.

### Removed
- Removed the `isbn` property from the `Livro` Entity.

### Fixed
- N/A.


## 2023-08-23

### Added
- Implemented the `Avaliacao` DTO.


- Implemented the `AvaliacaoDTO` mapper.

### Changed
- Updated the `Avaliacao` controller to use the `AvaliacaoDTO` in these endpoints:
  - `GET` `api/v1/avaliacao/all` - Now returns a list of `AvaliacaoDTO`.
  - `GET` `api/v1/avaliacao/{avaliacaoId}` - Now returns a `AvaliacaoDTO`.


- Updated the `Avaliacao` service to use the `AvaliacaoDTO` mapper in these functions:
  - `getAllAvaliacoes` - Now returns a list of `AvaliacaoDTO`.
  - `getAvaliacaoById` - Now returns a `AvaliacaoDTO`.


- Updated the test `canGetAvaliacao` in `AvaliacaoServiceTest.java` to use the `AvaliacaoDTO` mapper. 

### Deprecated
- N/A.
### Removed
- N/A.
### Fixed
- N/A.


## 2023-08-10

### Added
- Implemented the `Avaliacao` model.


- Added the `api/v1/avaliacao` endpoint with the following methods:

    - `GET` `api/v1/avaliacao/all` - Returns a list of all reviews.
    - `GET` `api/v1/avaliacao/{avaliacaoId}` - Returns a review by its id.
    - `POST` `api/v1/avaliacao/new`- Creates a new review.
    - `DELETE` `api/v1/avaliacao/{avaliacaoId}`- Deletes a review by its id.
    - `PUT` `api/v1/avaliacao/{avaliacaoId}`- Updates a review by its id.


- Added the following queries to the `Avaliacao` repository:

    - `findByLivroTitle` - Returns a review by its book title.
    - `findByAuthor` - Returns a review by its author.


- Added the corresponding functions to the `Avaliacao` service.


- Added the unit tests for the `Avaliacao` repository.


- Added the unit tests for the `Avaliacao` service.


- Implemented the `Comentario` model.


- Added the `api/v1/comentario` endpoint with the following methods:

    - `GET` `api/v1/comentario/all` - Returns a list of all comments.
    - `GET` `api/v1/comentario/{comentarioId}` - Returns a comment by its id.
    - `POST` `api/v1/comentario/new`- Creates a new comment.
    - `DELETE` `api/v1/comentario/{comentarioId}`- Deletes a comment by its id.
    - `PUT` `api/v1/comentario/{comentarioId}`- Updates a comment by its id.


- Implemented the repository for  the `Comentario` model.


- Added the corresponding functions to the `Comentario` service.


- Added the unit tests for the `Comentario` repository.


- Added the unit tests for the `Comentario` service.

### Changed
- N/A.

### Deprecated
- N/A.

### Removed
- N/A.

### Fixed
- N/A.

## 2023-07-31

### Added
- Implemented the `Livro` model.


- Added the `api/v1/livro` endpoint with the following methods:

    - `GET` `api/v1/livro/all` - Returns a list of all books.
    - `GET` `api/v1/livro/{livroId}` - Returns a book by its id.
    - `POST` `api/v1/livro/new`- Creates a new book.
    - `DELETE` `api/v1/livro/{livroId}`- Deletes a book by its id.


- Added the following queries to the `Livro` repository:

    - `findByTitulo` - Returns a book by its title.
    - `findByAutor` - Returns a book by its author.
    - `findByIsbn` - Returns a book by its ISBN.

- Added the corresponding functions to the `Livro` service.


- Added the unit tests for the `Livro` repository.


- Added the unit tests for the `Livro` service.


### Changed
- N/A.

### Deprecated
- N/A.

### Removed
- N/A.

### Fixed
- N/A.
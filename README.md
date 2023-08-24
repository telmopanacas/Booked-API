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
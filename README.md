# Booked-API

# Description
- This is a RESTful API designed for this project: [Booked](https://www.figma.com/community/file/1266829619368231584)

# Change Log
Guide for writing change logs.

`Added` for new features.

`Changed` for changes in existing functionality.

`Deprecated` for soon-to-be removed features.

`Removed` for now removed features.

`Fixed`  for any bug fixes.

## 31/07/2023

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


### Deprecated


### Removed


### Fixed
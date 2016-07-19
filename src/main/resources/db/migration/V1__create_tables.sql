CREATE TABLE `author` (
  `idAuthor` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(128) NOT NULL,
  `lastName` varchar(128) NOT NULL,
  `authorInfo` text,
  PRIMARY KEY (`idAuthor`),
  UNIQUE KEY `idAuthor_UNIQUE` (`idAuthor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `book` (
  `idBook` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `isbn` varchar(64) NOT NULL,
  `description` text,
  `bookData` longblob NOT NULL,
  `creatorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`idBook`),
  UNIQUE KEY `idbook_UNIQUE` (`idBook`),
  KEY `bookCreatorId_idx` (`creatorId`),
  CONSTRAINT `bookCreatorId` FOREIGN KEY (`creatorId`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

CREATE TABLE `books_authors` (
  `bookId` int(11) NOT NULL,
  `authorId` int(11) NOT NULL,
  PRIMARY KEY (`bookId`,`authorId`),
  KEY `authorId_idx` (`authorId`),
  CONSTRAINT `authorBookId` FOREIGN KEY (`authorId`) REFERENCES `author` (`idAuthor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bookAuthorId` FOREIGN KEY (`bookId`) REFERENCES `book` (`idBook`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `genre` (
  `idGenre` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`idGenre`),
  UNIQUE KEY `idGenre_UNIQUE` (`idGenre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `books_genres` (
  `genreId` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  PRIMARY KEY (`genreId`,`bookId`),
  KEY `bookGenreId_idx` (`bookId`),
  CONSTRAINT `bookGenreId` FOREIGN KEY (`bookId`) REFERENCES `book` (`idBook`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `genreBookId` FOREIGN KEY (`genreId`) REFERENCES `genre` (`idGenre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `books_users` (
  `bookId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`bookId`,`userId`),
  KEY `userBookId_idx` (`userId`),
  CONSTRAINT `bookUserId` FOREIGN KEY (`bookId`) REFERENCES `book` (`idBook`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `userBookId` FOREIGN KEY (`userId`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# Database Table 생성 쿼리문 + mongoDB insert 쿼리문

## User table
> CREATE TABLE users (
  	id INT(255) NOT NULL AUTO_INCREMENT,
  	email VARCHAR(255)  NOT NULL,
  	username VARCHAR(255) NOT NULL,
  	password VARCHAR(255) NOT NULL,
  	phoneNumber VARCHAR(255) NOT NULL,
      isMember INT(3)  NOT NULL DEFAULT '1',
      admin INT(3) UNSIGNED NOT NULL DEFAULT '0',
  	PRIMARY KEY (`id`),
      UNIQUE INDEX `username` (`username`)
  );

+ insert into users(email, username, password, phoneNumber)
  values('test@naver.com','seol','1234567','010-0000-0000');
## Log table
> create table result_log(
    	id int(20) auto_increment primary key not null,
      user_name varchar(255) not null,
      created_date datetime,
      download_cnt int(11),
      download_date datetime,
      file_name varchar(255) not null,
      result_file_name varchar(255),
      is_succeed int(1),
      origin_location varchar(255) not null,
      result_location varchar(255)
    );
>
>
+   insert into result_log(user_name, created_date, file_name, result_file_name, origin_location, result_location) 
  values("test", now(), "hoho.pdf","done.pdf", "/c/d/f/", "C:\\Users\\smart\\Desktop\\hoho.pdf");

## Fileinfo table
> create table fileinfo(
  	id int(20) auto_increment primary key not null,
      file_name varchar(255) not null,
      upload_date datetime,
      upload_position varchar(255) not null    
  );

+ insert into fileinfo(file_name, upload_position)values('upload1', 'c://users/upload1');

---
## mongoDB Data insert query 문
> Database: sixthsense
> collection: SummaryData
> {"originLocation":"/Users/griffindouble/downloads/police.csv","fileName":"police.csv","userName":"test1","info":[{"colName":"연번","summary":{"dataType":"String","deIdentified":"masking","prove":"K"}},{"colName":"성별","summary":{"dataType":"Integer","deIdentified":"interval","prove":"L"}},{"colName":"적발횟수","summary":{"dataType":"Integer","deIdentified":"interval","prove":"T"}},{"colName":"나이","summary":{"dataType":"Integer","deIdentified":"interval","prove":"T"}},{"colName":"알콜농도","summary":{"dataType":"Integer","deIdentified":"interval","prove":"T"}},{"colName":"측정일시","summary":{"dataType":"Integer","deIdentified":"interval","prove":"T"}},{"colName":"관할경찰서","summary":{"dataType":"Integer","deIdentified":"interval","prove":"T"}}]} 
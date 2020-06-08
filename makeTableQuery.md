# Database Table 생성 쿼리문

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
    is_succeed int(1),
    origin_location varchar(255) not null,
   result_location varchar(255)
  );
>
>
+ insert into reuslt_log(file_name, created_date, origin_location) values('first_file', now(), 'c://users/hpe1'); 

## Fileinfo table
> create table fileinfo(
  	id int(20) auto_increment primary key not null,
      file_name varchar(255) not null,
      upload_date datetime,
      upload_position varchar(255) not null    
  );

+ insert into fileinfo(file_name, upload_position)values('upload1', 'c://users/upload1');
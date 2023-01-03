create table specialty(
name varchar(10) primary key 
);
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('L1 MI');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('L2 MI');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('L3 GL');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('L3 TI');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('L3 SCI');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('L3 SI');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('M1 GL');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('M2 GL');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('M1 SITW');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('M2 SITW');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('M1 STIC');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('M2 STIC');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('M1 RSD');
INSERT INTO `TQL_APP_BD`.`specialty` (`name`) VALUES ('M2 RSD');


Create table subjects (
name varchar(10) primary key,
specialty varchar(10) ,
foreign key (specialty) references specialty(name)
);

INSERT INTO `TQL_APP_BD`.subjects (`name`, `specialty`) VALUES ('DAC', 'L3 GL');
INSERT INTO `TQL_APP_BD`.subjects (`name`, `specialty`) VALUES ('GL2', 'L3 GL');
INSERT INTO `TQL_APP_BD`.subjects (`name`, `specialty`) VALUES ('ASD', 'L2 MI');
INSERT INTO `TQL_APP_BD`.subjects (`name`, `specialty`) VALUES ('DAAW', 'L3 GL');
INSERT INTO `TQL_APP_BD`.subjects (`name`, `specialty`) VALUES ('DAM', 'L3 TI');
INSERT INTO `TQL_APP_BD`.subjects (`name`, `specialty`) VALUES ('TQL', 'L3 GL');
INSERT INTO `TQL_APP_BD`.subjects (`name`, `specialty`) VALUES ('GPL', 'L3 GL');
INSERT INTO `TQL_APP_BD`.subjects (`name`, `specialty`) VALUES ('TABD', 'L3 GL');
INSERT INTO `TQL_APP_BD`.`subjects` (`name`, `specialty`) VALUES ('AO', 'L2 MI');


create table teacherSubjects(
	email varchar(40) ,
    subjects varchar(10) ,
    primary key (email,subjects),
    foreign key (email) references users(user_email) ,
    foreign key (subjects) references subjects(name)
);

Create table teachingWeeks (
	id int auto_increment primary key,
    subjects varchar(10),
    week_start date,
    week_end date,
    foreign key (subjects) references subjects(name)
);

create table subjectDesc (
	teachingWeek int primary key,
    descriptionInfo varchar(100),
    foreign key (teachingWeek) references teachingWeeks(id)
);

create table subjectCour (
	cour_link varchar(200) primary key,
    cour_name varchar(50),
    teachingWeek int ,
    foreign key (teachingWeek) references teachingWeeks(id)
);

create table subjectTD (
	td_link varchar(200) primary key,
    td_name varchar(50),
    teachingWeek int ,
    foreign key (teachingWeek) references teachingWeeks(id)
);

create table subjectTP (
	tp_link varchar(200) primary key,
    tp_name varchar(50),
    teachingWeek int ,
    foreign key (teachingWeek) references teachingWeeks(id)
);


INSERT INTO `TQL_APP_BD`.`teacherSubjects` (`email`, `subjects`) VALUES ('marbel@gmail.com', 'DAC');
INSERT INTO `TQL_APP_BD`.`teacherSubjects` (`email`, `subjects`) VALUES ('marbel@gmail.com', 'AO');


INSERT INTO `TQL_APP_BD`.`teachingWeeks` (`subjects`, `week_start`, `week_end`) VALUES ('DAC', '2022-10-04', '2022-10-12');
INSERT INTO `TQL_APP_BD`.`teachingWeeks` (`subjects`, `week_start`, `week_end`) VALUES ('DAC', '2022-10-13', '2022-10-20');

INSERT INTO `TQL_APP_BD`.`teachingWeeks` (`id`, `subjects`, `week_start`, `week_end`) VALUES (NULL, 'AO', '2022-09-15', '2022-09-21');
INSERT INTO `TQL_APP_BD`.`teachingWeeks` (`subjects`, `week_start`, `week_end`) VALUES ('AO', '2022-10-14', '2022-10-24');


INSERT INTO `TQL_APP_BD`.`subjectDesc` (`teachingWeek`, `descriptionInfo`) VALUES ('1', 'Introduction to Threads');
INSERT INTO `TQL_APP_BD`.`subjectDesc` (`teachingWeek`, `descriptionInfo`) VALUES ('2', 'Semaphores and the philosephor\'s problem');

INSERT INTO `TQL_APP_BD`.`subjectCour` (`cour_link`, `cour_name`, `teachingWeek`) VALUES ('https://elearning.univ-constantine2.dz/elearning/pluginfile.php/109345/mod_folder/content/0/IntroductionThreadJava.pdf?forcedownload=1', 'Cour 01 (Threads).pdf', '1');
INSERT INTO `TQL_APP_BD`.`subjectCour` (`cour_link`, `cour_name`, `teachingWeek`) VALUES ('https://drive.google.com/file/d/1Q72UHhmaB2LmNX8OQoyNyiyoXmap5Y25/view', 'Cour 02 (Semaphores).pdf', '2');

INSERT INTO `TQL_APP_BD`.`subjectTP` (`tp_link`, `tp_name`, `teachingWeek`) VALUES ('https://drive.google.com/file/d/1s_eOmctOYhmbALxJpcgJ2WazNI8-eJu4/view', 'TP 01', '2');

INSERT INTO `TQL_APP_BD`.`subjectTD` (`td_link`, `td_name`, `teachingWeek`) VALUES ('https://drive.google.com/file/d/1tvhM7U19YcfgSozig4LdyJd7Dh7c5WiU/view', 'TD 02', '2');

INSERT INTO `TQL_APP_BD`.`subjectDesc` (`teachingWeek`, `descriptionInfo`) VALUES ('3', 'Introduction to Archetecture of Programs');
INSERT INTO `TQL_APP_BD`.`subjectDesc` (`teachingWeek`, `descriptionInfo`) VALUES ('4', 'Understading Multi Level Complex Computer Systems');

INSERT INTO `TQL_APP_BD`.`subjectCour` (`cour_link`, `cour_name`, `teachingWeek`) VALUES ('https://drive.google.com/file/d/1vfueNEpoCtuz7tsmsM5WTbttO-6RXGKA/view?usp=sharing', 'Cour 01 (Introduction).pdf', '3');
INSERT INTO `TQL_APP_BD`.`subjectCour` (`cour_link`, `cour_name`, `teachingWeek`) VALUES ('https://drive.google.com/file/d/1bycy9QP7BMR6dKOsdIDLjbQf9FhnOuoh/view?usp=sharing', 'Cour 02 (Complex Systems).pdf', '4');

INSERT INTO `TQL_APP_BD`.`subjectTP` (`tp_link`, `tp_name`, `teachingWeek`) VALUES ('https://drive.google.com/file/d/17KejmFhS2Ch1UUCZ9vfFyWDuI9R8jbOP/view', 'TP 01', '4');



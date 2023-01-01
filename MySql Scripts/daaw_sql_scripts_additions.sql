ALTER TABLE users DROP CONSTRAINT CHECK_User_Rank ;
ALTER TABLE users
ADD CONSTRAINT CHECK_User_Rank 
CHECK (user_rank='student' OR user_rank='teacher' ) ;

ALTER TABLE users DROP CONSTRAINT CHECK_User_Email ;
ALTER TABLE users 
ADD CONSTRAINT CHECK_User_Email
CHECK  (user_email REGEXP '^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9][a-zA-Z0-9._-]*\\.[a-zA-Z]{2,4}$');

ALTER TABLE admins DROP CONSTRAINT CHECK_Admin_Email ;
ALTER TABLE admins 
ADD CONSTRAINT CHECK_Admin_Email
CHECK  (admin_email REGEXP '^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9][a-zA-Z0-9._-]*\\.[a-zA-Z]{2,4}$');
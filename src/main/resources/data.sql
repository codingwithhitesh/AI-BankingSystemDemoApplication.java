INSERT INTO users (name) VALUES ('Hitesh Sharma');
INSERT INTO users (name) VALUES ('Rahul Verma');
INSERT INTO users (name) VALUES ('Anjali Gupta');
INSERT INTO users (name) VALUES ('Amit Meena');
INSERT INTO users (name) VALUES ('Pooja Mishra');
INSERT INTO users (name) VALUES ('Vikram Singh');
INSERT INTO users (name) VALUES ('Neha Joshi');
INSERT INTO users (name) VALUES ('Sandeep Yadav');
INSERT INTO users (name) VALUES ('Deepak Choudhary');
INSERT INTO users (name) VALUES ('Kriti Saxena');


-- Link some test bank accounts to your new users
INSERT INTO bank_account (account_type, balance, type, user_id) VALUES ('SAVINGS', 65000.00, 'Savings', 1);
INSERT INTO bank_account (account_type, balance, type, user_id) VALUES ('CURRENT', 120000.00, 'Current', 2);
INSERT INTO bank_account (account_type, balance, type, user_id) VALUES ('SAVINGS', 8500.00, 'Savings', 3);
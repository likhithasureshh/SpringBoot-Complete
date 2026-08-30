INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Rahul Sharma', 'rahul@gmail.com', 'A_POSITIVE', 'Male', 28);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Priya Nair', 'priya@gmail.com', 'B_POSITIVE', 'Female', 25);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Arjun Kumar', 'arjun@gmail.com', 'O_POSITIVE', 'Male', 35);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Sneha Reddy', 'sneha@gmail.com', 'AB_POSITIVE', 'Female', 31);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Vikram Singh', 'vikram@gmail.com', 'A_NEGATIVE', 'Male', 42);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Ananya Das', 'ananya@gmail.com', 'B_NEGATIVE', 'Female', 22);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Karan Mehta', 'karan@gmail.com', 'AB_NEGATIVE', 'Male', 29);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Meera Iyer', 'meera@gmail.com', 'A_POSITIVE', 'Female', 38);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Rohit Verma', 'rohit@gmail.com', 'B_POSITIVE', 'Male', 26);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Divya Menon', 'divya@gmail.com', 'AB_POSITIVE', 'Female', 33);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Aditya Rao', 'aditya@gmail.com', 'A_NEGATIVE', 'Male', 45);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Pooja Shah', 'pooja@gmail.com', 'B_POSITIVE', 'Female', 27);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Suresh Patil', 'suresh@gmail.com', 'A_POSITIVE', 'Male', 51);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Neha Kapoor', 'neha@gmail.com', 'B_NEGATIVE', 'Female', 30);

INSERT INTO patient (name, email, blood_group, gender, age)
VALUES ('Manoj Joshi', 'manoj@gmail.com', 'AB_NEGATIVE', 'Male', 40);

INSERT INTO doctor (name, specialization, email, created_at)
VALUES
    ('Dr. Rajesh Kumar', 'Cardiology', 'rajesh.kumar@gmail.com', '2025-01-10 09:30:00'),
    ('Dr. Priya Sharma', 'Neurology', 'priya.sharma@gmail.com', '2025-01-15 10:00:00'),
    ('Dr. Arjun Rao', 'Orthopedics', 'arjun.rao@gmail.com', '2025-02-01 11:15:00'),
    ('Dr. Sneha Reddy', 'Pediatrics', 'sneha.reddy@gmail.com', '2025-02-10 09:45:00'),
    ('Dr. Kiran Patel', 'Dermatology', 'kiran.patel@gmail.com', '2025-02-20 14:00:00'),
    ('Dr. Ananya Mehta', 'Gynecology', 'ananya.mehta@gmail.com', '2025-03-05 10:30:00'),
    ('Dr. Vikram Singh', 'General Medicine', 'vikram.singh@gmail.com', '2025-03-12 12:00:00'),
    ('Dr. Neha Kapoor', 'Oncology', 'neha.kapoor@gmail.com', '2025-03-20 09:00:00'),
    ('Dr. Rahul Verma', 'ENT', 'rahul.verma@gmail.com', '2025-04-01 15:30:00'),
    ('Dr. Meera Nair', 'Ophthalmology', 'meera.nair@gmail.com', '2025-04-10 11:00:00');


INSERT INTO insurance (id, policy_number, provider, valid_until, created_at)
VALUES
    (nextval('insurance_seq'), 'POL1001', 'Star Health Insurance', '2027-01-15', '2025-01-10 10:00:00');

INSERT INTO insurance (id, policy_number, provider, valid_until, created_at)
VALUES
    (nextval('insurance_seq'), 'POL1002', 'HDFC ERGO', '2027-02-20', '2025-01-15 11:30:00');

INSERT INTO insurance (id, policy_number, provider, valid_until, created_at)
VALUES
    (nextval('insurance_seq'), 'POL1003', 'ICICI Lombard', '2027-03-10', '2025-02-01 09:45:00');

INSERT INTO insurance (id, policy_number, provider, valid_until, created_at)
VALUES
    (nextval('insurance_seq'), 'POL1004', 'Bajaj Allianz', '2027-04-25', '2025-02-10 14:15:00');

INSERT INTO insurance (id, policy_number, provider, valid_until, created_at)
VALUES
    (nextval('insurance_seq'), 'insurance_seq', 'Niva Bupa', '2027-05-18', '2025-02-20 12:00:00');
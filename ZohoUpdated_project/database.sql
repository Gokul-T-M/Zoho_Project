create database db;
use db;

create table vendors(
vendor_id int auto_increment primary key,
vendor_name varchar(30),
bank_acct_no varchar(30),
unique(vendor_name,bank_acct_no)
);

select*from vendors;
/*
insert into vendors(vendor_name,bank_acct_no) values('a','1000'),
('b','1001'),('c','1002');
*/
create table bills(
bill_no int auto_increment primary key,
vendor_id int,
amt decimal(10,2),
amtpaid decimal(10,2),
bill_date date,
foreign key (vendor_id) references vendors(vendor_id)
);

/*
-- adding a bill:

insert into bills(vendor_id,amt,amtpaid,bill_date) values (1,'5000','4000',str_to_date('15-03-2003','%d-%m-%Y')),
(2,'4000','3500',str_to_date('20-04-2004','%d-%m-%Y')),
(1,'2000','2000',str_to_date('27-07-2005','%d-%m-%Y')),
(3,'4850','4500',str_to_date('25-10-2005','%d-%m-%Y'));
insert into bills(vendor_id,amt,amtpaid,bill_date) values (1,'2100','2000',str_to_date('25-03-2003','%d-%m-%Y'));

select*from bills;

drop table bills;
drop table vendors;


-- getting details of vendor with vendor_id: 1(retriving all bills of a vendor):

select vendor_id,vendor_name,bank_acct_no from vendors where vendor_id=1;
select bill_no,amt,amtpaid,bill_date,amt-amtpaid as balance from bills where vendor_id=1;

-- updating bills(paying balances)

update bills
set amtpaid=amtpaid+400
where bill_no=4 and amtpaid+400<amt;


-- total paid amount to each vendor

SELECT 
    v.vendor_id,
    v.vendor_name,
    v.bank_acct_no,
    COALESCE(SUM(b.amtpaid), 0) AS total_paid_amount
FROM 
    vendors v
LEFT JOIN 
    bills b ON v.vendor_id = b.vendor_id
GROUP BY 
    v.vendor_id, v.vendor_name, v.bank_acct_no;

-- printing all the bills of all the vendors

SELECT
    v.vendor_id,
    v.vendor_name,
    v.bank_acct_no,
    b.bill_no,
    b.amt,
    b.amtpaid,
    b.bill_date
FROM 
    vendors v
JOIN 
    bills b ON v.vendor_id = b.vendor_id
ORDER BY 
    v.vendor_name;
    
-- Print Payments by Month

SELECT
    DATE_FORMAT(b.bill_date, '%Y-%m') AS payment_month,
    SUM(b.amtpaid) AS total_paid
FROM
    bills b
GROUP BY
    payment_month
ORDER BY
    payment_month DESC;

-- Print Payments by within date range

SELECT
    v.vendor_id,
    v.vendor_name,
    v.bank_acct_no,
    b.bill_no,
    b.amt,
    b.amtpaid,
    b.bill_date
FROM
    vendors v
JOIN
    bills b  ON v.vendor_id = b.vendor_id
 WHERE
    b.bill_date BETWEEN '2003-01-01' AND '2003-12-31'
ORDER BY
    b.bill_date;
    
-- top paid vendor

insert into vendors(vendor_name,bank_acct_no) values ('d','1003');

SELECT 
    v.vendor_id,
    v.vendor_name,
    v.bank_acct_no,
    coalesce(sum(b.amtpaid),0) AS total_paid_amount
FROM 
    vendors v
LEFT JOIN 
    bills b  ON v.vendor_id = b.vendor_id
GROUP BY 
    v.vendor_id
ORDER BY 
    total_paid_amount DESC
limit 1;


-- printing balance of all vendors

SELECT
    v.vendor_id,
    v.vendor_name,
    v.bank_acct_no,
    COALESCE(SUM(b.amt), 0) AS total_billed_amount,
    COALESCE(SUM(b.amtpaid), 0) AS total_paid_amount,
    COALESCE(SUM(b.amt), 0) - COALESCE(SUM(b.amtpaid), 0) AS balance
FROM
    vendors v
LEFT JOIN
    bills b ON v.vendor_id = b.vendor_id
GROUP BY
    v.vendor_id, v.vendor_name, v.bank_acct_no
ORDER BY
    balance DESC;

*/

    

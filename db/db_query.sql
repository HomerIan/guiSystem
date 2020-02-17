

--FOR SHOWING THE LIST OF ITEMS
CREATE VIEW customer_VIEW AS
SELECT c.custName, s.custNo, s.transNo 
	FROM customer c
	RIGHT JOIN sales s
	ON c.custNo = s.custNo;


SELECT cv.transNo,p.description, p.price,sd.quantity, SUM(p.price*sd.quantity) AS 'amount'
	FROM customer_VIEW cv
	RIGHT JOIN salesdetail sd
	ON cv.transNo = sd.transNo
	RIGHT JOIN product p
	ON sd.prodcode = p.prodcode
	WHERE cv.custName = 'Homer Reyes'
	GROUP BY cv.transNo,p.description, p.price,sd.quantity;

--TOTAL AMOUNT FOR EACH TRANSACTION
SELECT sd.transNo,SUM(p.price*sd.quantity) AS 'Total Amount'
	FROM salesdetail sd
	RIGHT JOIN product p
	ON sd.prodcode = p.prodcode
	RIGHT JOIN customer_VIEW cv
	ON sd.transNo = cv.transNo
	WHERE sd.transNo = cv.transNo
	GROUP BY sd.transNo;

SELECT sd.transNo,SUM(p.price*sd.quantity) AS 'Total Amount'\r\n" + 
									"	FROM salesdetail sd\r\n" + 
									"	RIGHT JOIN product p\r\n" + 
									"	ON sd.prodcode = p.prodcode\r\n" + 
									"	RIGHT JOIN customer_VIEW cv\r\n" + 
									"	ON sd.transNo = cv.transNo\r\n" + 
									"	WHERE cv.custName = '"+rs.getString("custname")+"'\n" + 
									"	GROUP BY sd.transNo;\r\n";


--GET TOTAL WEIGHT AND VALUE OF PRODUCT EVERY CUSTOMER
SELECT s.custNo,sd.transNo, SUM(p.price*sd.quantity) AS 'Total Amount', SUM(p.weight*sd.quantity) AS 'Total weight'
	FROM sales s
	RIGHT JOIN salesdetail sd
	ON s.transNo = sd.transNo
	RIGHT JOIN product p
	ON sd.prodcode = p.prodcode
	GROUP BY s.custNo,sd.transNo;	

TR01
total amount = 340
total weight = 90


0,1,2,3,4,5[] = 6
0,1,2,3,4,5[] = 6

0,0
0,1-
0,2-
0,3-
0,4-
0,5-
1,2-
1,3-
1,4-
1,5-
2,3-
2,4-
2,5-
3,4-
3,5-
4,5-
1,2,3-
1,2,4-
1,2,5-
1,3,4-
1,3,5-
1,4,5-
2,3,4-
2,3,5-
2,4,5-
3,4,5-

1,2,3,4-
1,2,3,5-
1,2,4,5-

1,3,4,5-
2,3,4,5-

1,2,3,4,5-	





	

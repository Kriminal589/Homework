INSERT INTO organization (inn, checking_account)
VALUES ('75643753', '56747564'),
       ('567353345', '56745434'),
       ('2343455', '56756743'),
       ('12324', '8970138'),
       ('4983751', '58697983'),
       ('59867832', '609784093');


INSERT INTO invoice (organization_id, date)
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '75643753'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '567353345'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '2343455'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '12324'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '4983751'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '59867832';


INSERT INTO position_invoice (invoice_id, product_id, price, count)
SELECT a.id, b.id, 2000, 1
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '75643753')
UNION
SELECT a.id, b.id, 5000, 2
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '125' and organization.inn = '567353345')
UNION
SELECT a.id, b.id, 3400, 24
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '124' and organization.inn = '2343455')
UNION
SELECT a.id, b.id, 2000, 4
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '12324')
UNION
SELECT a.id, b.id, 2000, 5
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '4983751')
UNION
SELECT a.id, b.id, 2000, 18
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '59867832')
UNION
SELECT a.id, b.id, 7000, 7
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '125' and organization.inn = '75643753');
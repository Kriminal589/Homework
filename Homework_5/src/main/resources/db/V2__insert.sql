INSERT INTO product (name, code)
VALUES ('Book', '123'),
       ('Pen', '124'),
       ('Mouse', '125'),
       ('Pan', '126');

INSERT INTO organization (inn, checking_account)
VALUES ('34165136', '38462844'),
       ('324792674', '2983498427'),
       ('38947284', '2894729834'),
       ('2937453847', '29348723'),
       ('43895729', '049585209'),
       ('23742984', '294572985');

INSERT INTO invoice (organization_id, date)
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '34165136'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '324792674'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '38947284'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '2937453847'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '43895729'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '23742984'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '34165136'
UNION
SELECT id, CURRENT_DATE
FROM organization
WHERE inn = '38947284';

INSERT INTO position_invoice (invoice_id, product_id, price, count)
SELECT a.id, b.id, 2000, 1
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '34165136')
UNION
SELECT a.id, b.id, 5000, 2
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '125' and organization.inn = '34165136')
UNION
SELECT a.id, b.id, 3400, 3
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '124' and organization.inn = '34165136')
UNION
SELECT a.id, b.id, 2000, 4
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '324792674')
UNION
SELECT a.id, b.id, 2000, 5
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '324792674')
UNION
SELECT a.id, b.id, 2000, 6
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '38947284')
UNION
SELECT a.id, b.id, 2000, 7
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '38947284')
UNION
SELECT a.id, b.id, 2000, 8
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '2937453847')
UNION
SELECT a.id, b.id, 2000, 9
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '43895729')
UNION
SELECT a.id, b.id, 2000, 10
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '23742984')
UNION
SELECT a.id, b.id, 2000, 11
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '23742984')
UNION
SELECT a.id, b.id, 2000, 12
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '23742984')
UNION
SELECT a.id, b.id, 2000, 13
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '23742984')
UNION
SELECT a.id, b.id, 2000, 14
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '34165136')
UNION
SELECT a.id, b.id, 2000, 15
FROM invoice as a, product as b, organization
WHERE (a.organization_id = organization.id and b.code = '123' and organization.inn = '34165136');


INSERT INTO company (company_name)
VALUES ('Google');
INSERT INTO company (company_name)
VALUES ('Microsoft');
INSERT INTO company (company_name)
VALUES ('Apple');


INSERT INTO part_master(id, part_name)
VALUES (1, 'CPU');
INSERT INTO part_master(id, part_name)
VALUES (2, 'Memory');
INSERT INTO part(id, part_master_id, process_id, model_number, stock_quantity, last_ordered_at)
VALUES (1, 1, 100, 'CPU-001', 10, '2026-05-01');

INSERT INTO part(id, part_master_id, process_id, model_number, stock_quantity, last_ordered_at)
VALUES (2, 1, 100, 'CPU-002', 5, '2026-05-02');
INSERT INTO part(id, part_master_id, process_id, model_number, stock_quantity, last_ordered_at)
VALUES (3, 2, 200, 'MEM-001', 20, '2026-05-03');

ALTER TABLE part_master
    ALTER COLUMN id RESTART WITH 3;
ALTER TABLE part
    ALTER COLUMN id RESTART WITH 4;


INSERT INTO stock_history_part
    (id, part_id, quantity, action_at)
VALUES (1, 1, 10, '2026-05-01');
INSERT INTO stock_history_part
    (id, part_id, quantity, action_at)
VALUES (2, 1, -3, '2026-05-02');
INSERT INTO stock_history_part(id, part_id, quantity, action_at)
VALUES (3, 2, 5, '2026-05-03');

ALTER TABLE stock_history_part
    ALTER COLUMN id RESTART WITH 4;


INSERT INTO manufacturing_process
    (id, process_name, sort_order)
VALUES (1, '切断', 1);
INSERT INTO manufacturing_process
    (id, process_name, sort_order)
VALUES (2, '加工', 2);
INSERT INTO manufacturing_process
    (id, process_name, sort_order)
VALUES (3, '組立', 3);

ALTER TABLE manufacturing_process
    ALTER COLUMN id RESTART WITH 4;


INSERT INTO product
(id, product_name, company_id, process_id,
 model_number, stock_quantity, last_ordered_at)
VALUES (1, 'Laptop', 1, 100,
        'LP-001', 50, '2026-05-01');
INSERT INTO product
(id, product_name, company_id, process_id,
 model_number, stock_quantity, last_ordered_at)
VALUES (2, 'Mouse', 1, 100,
        'MS-001', 10, '2026-05-10');
INSERT INTO product
(id, product_name, company_id, process_id,
 model_number, stock_quantity, last_ordered_at)
VALUES (3, 'Keyboard', 2, 200,
        'KB-001', 30, '2026-04-01');

ALTER TABLE product
    ALTER COLUMN id RESTART WITH 4;
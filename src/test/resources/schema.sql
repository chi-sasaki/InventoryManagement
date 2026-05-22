CREATE TABLE company (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         company_name VARCHAR(255) NOT NULL
);

CREATE TABLE part_master (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             part_name VARCHAR(255) NOT NULL
);

CREATE TABLE part (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      part_master_id BIGINT NOT NULL,
                      process_id BIGINT,
                      model_number VARCHAR(255) NOT NULL,
                      stock_quantity INTEGER NOT NULL,
                      last_ordered_at DATE,

                      CONSTRAINT fk_part_master
                          FOREIGN KEY (part_master_id)
                              REFERENCES part_master(id)
);

CREATE TABLE stock_history_part (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    part_id BIGINT NOT NULL,
                                    quantity INT NOT NULL,
                                    action_at DATE NOT NULL,

                                    CONSTRAINT fk_stock_history_part
                                        FOREIGN KEY (part_id)
                                            REFERENCES part(id)
);

CREATE TABLE manufacturing_process (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       process_name VARCHAR(255) NOT NULL,
                                       sort_order INTEGER NOT NULL
);

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         product_name VARCHAR(255) NOT NULL,
                         company_id BIGINT NOT NULL,
                         process_id BIGINT,
                         model_number VARCHAR(255) NOT NULL,
                         stock_quantity INTEGER NOT NULL,
                         last_ordered_at DATE,

                         CONSTRAINT fk_product_company
                             FOREIGN KEY (company_id)
                                 REFERENCES company(id)
);
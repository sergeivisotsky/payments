INSERT INTO type_entity VALUES (1, '2e294f1c-8916-44b2-99ca-d2199ab1c796', 'TypeOneEntity', 3200, 'EUR', 'AVC', 'AVP', 'This is type one', null);
INSERT INTO type_entity VALUES (2, 'fd04c64a-c8d1-4dd3-811f-87e9be3f97f5', 'TypeTwoEntity', 6000, 'USD', 'AVC', 'AVP', 'This is type two', null);
INSERT INTO type_entity VALUES (3, '4130e401-caea-44bc-95b9-76d299ef078f', 'TypeThreeEntity', 9000, 'EUR', 'AVC', 'AVP', null, 'BICCODEeee');
INSERT INTO type_entity VALUES (4, '0f96e409-66ef-4897-a48e-911714817d54', 'TypeThreeEntity', 9000, 'USD', 'AVC', 'AVP', null, 'BICCODEeee');

INSERT INTO payment_summary VALUES (1, '7ba7b804-6909-465a-9b2d-1b6fcb3e0680', 5000.0, 'John', 'Smith', 'LVS-111111', 'ISSUED', '2020-01-10 19:06:36.651288', null, 1);
INSERT INTO payment_summary VALUES (2, 'b5a43bbc-68e6-40ab-9ece-f871c997a282', 100.0, 'Entony', 'Smith', 'LVA-111333111', 'ISSUED', '2020-01-09 19:06:36.651288', null, 1);
INSERT INTO payment_summary VALUES (3, '7082a315-d130-4a48-848e-1bd6ab58bc22', 100.0, 'Entony', 'Smith', 'LVA-111333111', 'ISSUED', '2020-01-11 08:22:36.651288', null, 1);

INSERT INTO cancellation_coefficients VALUES (1, 'TypeOneEntity', 0.05);
INSERT INTO cancellation_coefficients VALUES (2, 'TypeTwoEntity', 0.1);
INSERT INTO cancellation_coefficients VALUES (3, 'TypeThreeEntity', 0.15);

INSERT INTO error_messages VALUES (1, 'TPE_001', 'Required type is not found');
INSERT INTO error_messages VALUES (2, 'PAY_001', 'Payment with this number not found');
INSERT INTO error_messages VALUES (3, 'PAY_002', 'Payment can be cancelled only on the day of creation before 00:00');

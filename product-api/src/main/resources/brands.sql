INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 1, '2024-08-18 21:06:34.434137', 'Apple');
INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 2, '2024-08-18 21:06:34.434137', 'Microsoft');
INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 3, '2024-08-18 21:06:34.434137', 'Google');
INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 4, '2024-08-18 21:06:34.434137', 'Amazon');
INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 5, '2024-08-18 21:06:34.434137', 'Samsung');
INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 6, '2024-08-18 21:06:34.434137', 'HP');
INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 7, '2024-08-18 21:06:34.434137', 'Dell');
INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 8, '2024-08-18 21:06:34.434137', 'Lenovo');
INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 9, '2024-08-18 21:06:34.434137', 'Asus');
INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 10, '2024-08-18 21:06:34.434137', 'Acer');
INSERT INTO public.brand (created_at, id, updated_at, name) VALUES ('2024-08-18 21:06:34.434137', 11, '2024-08-18 21:06:34.434137', 'Oreilly Media');

select setval('brand_id_seq', (select max(id) from brand));
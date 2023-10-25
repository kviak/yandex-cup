SELECT DISTINCT pp.id
FROM pickup_point pp
         LEFT JOIN brand_data bd ON pp.id = bd.pickup_point_id
WHERE pp.branded_since = '2023-10-25' AND bd.pickup_point_id IS NULL;
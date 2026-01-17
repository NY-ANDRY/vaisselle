#!/bin/bash

output_file="sql/base.txt"
tables=(
"t_carts" 
"t_cart_details" 
"t_categories" 
"t_colors"
"t_colors_up"
"t_discount_cart"
"t_models"
"t_movements"
"t_movement_types"
"t_products"
"t_product_images"
"t_sizes"
"t_types" 
"t_users"
)

echo "tables structure" > "$output_file"
echo "" >> "$output_file"

for table in "${tables[@]}"; do
    echo "$table" >> "$output_file"
    sudo psql -U abc -d vaisselle -t -A -c "\d $table" | sudo tee -a "$output_file" > /dev/null
    echo "" >> "$output_file"
done

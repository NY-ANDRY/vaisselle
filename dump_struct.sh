#!/bin/bash

output_file="sql/base.txt"
tables=(
"t_categories" 
"t_types" 
"t_models" 
"t_products" 
"t_models_types" 
"t_users"
"t_colors"
"t_products_colors"
"t_sizes"
)

echo "tables structure" > "$output_file"
echo "" >> "$output_file"

for table in "${tables[@]}"; do
    echo "$table" >> "$output_file"
    sudo psql -U abc -d vaisselle -t -A -c "\d $table" | sudo tee -a "$output_file" > /dev/null
    echo "" >> "$output_file"
done

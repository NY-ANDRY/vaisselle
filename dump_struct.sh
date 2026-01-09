#!/bin/bash

output_file="sql/base.txt"
tables=("t_categories" "t_matieres" "t_models" "t_products" "t_products_categories" "t_users")

echo "tables structure" > "$output_file"
echo "" >> "$output_file"

for table in "${tables[@]}"; do
    echo "$table" >> "$output_file"
    sudo psql -U abc -d vaisselle -t -A -c "\d $table" | sudo tee -a "$output_file" > /dev/null
    echo "" >> "$output_file"
done

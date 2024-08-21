# Use a imagem base oficial do PHP com Apache
FROM php:8.1-apache

# Copie o código da aplicação para o diretório raiz do Apache
COPY . /var/www/html/

# Defina as permissões corretas para os arquivos
RUN chown -R www-data:www-data /var/www/html \
    && chmod -R 755 /var/www/html

# Exponha a porta 80 para o servidor web
EXPOSE 80

# Habilite módulos adicionais do Apache, se necessário (opcional)
RUN a2enmod rewrite

# Defina o comando para iniciar o Apache
CMD ["apache2-foreground"]

FROM 			nginx:alpine

MAINTAINER		Felipe Rudolfe <feliperudolfe@outlook.com>

RUN 			rm -rf /usr/share/nginx/html/*

COPY 			config/nginx/nginx-custom.conf /etc/nginx/conf.d/default.conf
ADD 			config/init /opt/init
ADD 			dist/selecao-indra /usr/share/nginx/html

EXPOSE 			80

CMD 			["sh", "./opt/init/run.sh"]
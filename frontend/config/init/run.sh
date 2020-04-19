#!/bin/sh
grep -rHl "@BACKEND_ADDRESS@" ./usr/share/nginx/html/*.js | xargs sed -i "s|@BACKEND_ADDRESS@|$BACKEND_ADDRESS|g";
nginx -g "daemon off;"
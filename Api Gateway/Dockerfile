FROM node:18.17.1
WORKDIR /the/workdir/path
COPY ["package.json", "package-lock.json*", "./"]
RUN npm install
EXPOSE 3000
COPY . .
CMD ["node", "index.js"]

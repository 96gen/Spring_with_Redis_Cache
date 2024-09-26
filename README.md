# 書籍管理專案
使用RESTful API管理書籍，並且使用Redis快取，加快重複讀取資料的速度。

---

## 本專案使用的solution stack
- Spring Boot
- RESTful API
- MariaDB
- Redis
- Docker

---

## 如何啟動
有兩種方式
1. 自行使用Maven編譯後，執行產生的jar
2. 使用提供的docker-compose.yml和Dockerfile，利用Docker完成編譯和執行
可以使用以下指令來完成
```bash
docker-compose up
```

---

## 專案路由與功能介紹
| 路徑 | HTTP request method | 說明 | Request body |
| --- | --- | --- | --- |
| / | POST | 新增書籍 | Book |
| /all | GET | 取得全部的書籍 | 無 |
| /{id} | GET | 取得指定id的書籍 | 無 |
| /{id} | PUT | 修改書籍內容 | Book |
| /{id} | DELETE | 刪除書籍 | 無 |
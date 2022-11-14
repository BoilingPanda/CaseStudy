
from locust import HttpUser, task, between, events
import listeners

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0",
    "Accept-Encoding": "*",
    "Connection": "keep-alive"
}


class WebsiteUser(HttpUser):
    wait_time = between(5,10)
    host = "https://www.n11.com"

    def on_start(self):
        self.wait()

    @task(30)
    def index_page(self):
        response = self.client.get('/', headers=headers)
        assert response.status_code == 200
        assert response.headers.get('content-type') == "text/html;charset=UTF-8"

    @task(50)
    def search_page(self):
        response = self.client.get('/arama?q=oyuncu+bilgisayari', headers=headers)
        assert response.status_code == 200
        assert response.headers.get('content-type') == "text/html;charset=UTF-8"

    @task(50)
    def search_page_ordered(self):
        response = self.client.get('/arama?q=oyuncu+bilgisayari&srt=PRICE_LOW', headers=headers)
        assert response.status_code == 200
        assert response.headers.get('content-type') == "text/html;charset=UTF-8"

    @task(50)
    def search_page_filtered(self):
        response = self.client.get('/arama?q=oyuncu+bilgisayari&bellekkapasitesi=16+GB&urt=Oyuncu', headers=headers)
        assert response.status_code == 200
        assert response.headers.get('content-type') == "text/html;charset=UTF-8"


    @task(20)
    def basket_page(self):
        response = self.client.get('/sepetim', headers=headers)
        assert response.status_code == 200
        assert response.headers.get('content-type') == "text/html;charset=UTF-8"

    @task(10)
    def keyword_auto_complete(self):
        response = self.client.get('/arama/tamamla?keyword=b', headers=headers)
        assert response.status_code == 200
        assert response.headers.get('content-type') == "application/json;charset=UTF-8"

    @task(10)
    def account_info(self):
        response = self.client.get('/hesabim/hesap-bilgileri', headers=headers)
        assert response.status_code == 200
        assert response.headers.get('content-type') == "application/json;charset=UTF-8"

    @task(10)
    def cookie_usage_info(self):
        response = self.client.get('/component/render/cookieUsageInfo', headers=headers)
        assert response.status_code == 200
        assert response.headers.get('content-type') == "text/html;charset=UTF-8"

    @task(5)
    def storage_pusher(self):
        response = self.client.get('/storagePush.html', headers=headers)
        assert response.status_code == 200
        assert response.headers.get('content-type') == "text/html;charset=UTF-8"

Endpoint prices
=========================

Service provides an endpoint for companies historical prices from <a href="https://iextrading.com/developer/" rel="nofollow">IEX Developer Platform</a> saved in Google Datastore by `poll-prices` service.

You can define list of companies and interval for filtering (optional).
Examples of queries:

- `GET /v1/stocks?companies=AAPL,FB`
- `GET /v1/stocks?companies=AAPL,FB&from=2019-03-10 00:00`
- `GET /v1/stocks?companies=AAPL,FB&from=2019-03-10 00:00&to=2019-03-19 00:00`
- `GET /v1/stocks?companies=AAPL,FB&to=2019-03-19 00:00`
  
  
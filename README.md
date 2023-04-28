Readme file:

docker build --tag=loan-app:latest .
docker run -p8887:8082 loan-app:latest

To Scale up application:

docker-compose --file docker-compose.yml up -d --build --scale loan-app=2


SCALED THIS APPLICATION FOR 2 CONTAINERS:

$ docker ps
CONTAINER ID   IMAGE             COMMAND                  CREATED          STATUS          PORTS                     NAMES
6cb02a641969   loan-app:latest   "java -jar /loan-app…"   11 seconds ago   Up 7 seconds    0.0.0.0:18803->8082/tcp   loanapp_loan-app_3
1c641ed19c97   loan-app:latest   "java -jar /loan-app…"   15 seconds ago   Up 10 seconds   0.0.0.0:18802->8082/tcp   loanapp_loan-app_2


TEST URLS for backend Services
GET:
http://localhost:8887/api/v1/loan/loan-applications/1
RESPONSE:

[
    {
        "year": 2020,
        "month": 12,
        "profitOrLoss": 250000.0,
        "assetsValue": 1234.0
    },
    {
        "year": 2020,
        "month": 11,
        "profitOrLoss": 1150.0,
        "assetsValue": 5789.0
    },
    {
        "year": 2020,
        "month": 10,
        "profitOrLoss": 2500.0,
        "assetsValue": 22345.0
    },
    {
        "year": 2020,
        "month": 9,
        "profitOrLoss": -187000.0,
        "assetsValue": 223452.0
    }
]

URL:POST Request
http://localhost:8887/api/v1/loan/loan-applications/submit
BODY:
{
        "businessDetails": {
        "businessName": "ABC Corporation",
        "email": "abc@corporation.com",
        "loanAmount": 10000000,
        "yearEstablished": 2010,
        "accountingProvider": "Quickbooks"
        },
        "monthlySummaries": [
        {
        "year": 2022,
        "month": 1,
        "profitOrLoss": 25000,
        "assetsValue": 100000
        },
        {
        "year": 2022,
        "month": 2,
        "profitOrLoss": 15000,
        "assetsValue": 120000
        },
        {
        "year": 2022,
        "month": 3,
        "profitOrLoss": 25000,
        "assetsValue": 150000
        }
        ]
        }
RESPONSE: 

{
    "loanResult": "Success and Loan is favoured to be approved 60 percent of the requested value."
}
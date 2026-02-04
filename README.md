### サービス概要
本プロジェクトは、板金加工工場を想定した在庫管理システムです。  
各製造工程で使用する部品などの資材や、完成した製品の在庫を一元管理することができます。  
また、入庫・出庫履歴を記録し、指定した期間内の入出庫数を集計できる棚卸し機能も備えています。  

### 作成背景
板金加工工場に勤務していた際、担当工程で使用している大量の部品を、半年に一度、棚卸しをして在庫管理を行う必要がありました。当時は全て手書きで管理していたため、棚卸し作業に2〜3日かかり、通常業務が滞ってしまうことがありました。

また、完成した製品の在庫管理を任されたこともありましたが、何百種類とある製品の在庫状況は、梱包作業を行う従業員の記憶に頼って管理されていました。  
そのため、「最後に注文があったのはいつ頃か」といった情報を確認する際も、その都度従業員に尋ねる必要があり、非常に非効率だと感じていました。

これらの課題を解決するため、部品・製品の在庫情報や入出庫履歴を一元管理し、棚卸し作業を効率化できるアプリケーションが必要だと考え、本システムを作成しました。  

### 主な使用技術  
<img src="https://img.shields.io/badge/-Java%2021-orange.svg?logo=java&style=plastic">　<img src="https://img.shields.io/badge/-Spring%20Boot%204.0.1-2FCEA0.svg?logo=skyliner&style=plastic">　<img src="https://img.shields.io/badge/-Thymeleaf-20B8E5.svg?logo=tencentweibo&style=plastic">　<img src="https://img.shields.io/badge/Javascript-276DC3.svg?logo=javascript&style=flat">　<img src="https://img.shields.io/badge/-MySQL-4479A1.svg?logo=mysql&style=plastic">　<img src="https://img.shields.io/badge/-My%20Batis-BA141A.svg?logo=microsoftaccess&style=plastic">　<img src="https://img.shields.io/badge/-Postman-FF6C37.svg?logo=postman&style=plastic">　<img src="https://img.shields.io/badge/-Github-181717.svg?logo=github&style=plastic">　<img src="https://img.shields.io/badge/-Intellij%20IDEA-000000.svg?logo=intellijidea&style=plastic">    
  
  
### 機能一覧  
| 機能 | 詳細 |
| --- | --- |
| 部品の一覧表示 | 登録されている全ての部品情報を一覧で表示します |
| 部品の種類別検索 | プルダウンで部品の種類を指定し、該当する情報を表示します |
| 部品の登録 | 部品の名称、型番、数量、入庫日を登録します |
| 部品の更新 | 部品名から更新画面に遷移し、部品情報を更新します |
| 部品の棚卸し機能 | 指定期間内の入庫数・出庫数を集計し、表示します |
| 製品の一覧表示 | 製品の在庫情報を一覧で表示します |
| 会社名での絞り込み検索 | 取引先の会社を指定し、会社ごとの製品の一覧を表示します |
| 製品の登録 | 会社名、製品の名称、型番、数量、最終注文日を登録します |
| 製品の更新 | 製品名から更新画面に遷移し、製品情報を更新します |
| 製品の削除 | 製品情報を削除します |

  
＊ 言葉の定義は以下の通りです  
**製品**  
各工程を経て完成した製品を指します。  
不良品や追加注文に備えて在庫を保持することを想定し、工程には依存しない独立したオブジェクトとして設計しています。  
**棚卸し機能**  
半年に一度実施される棚卸し業務を想定し、指定した期間内の入庫数・出庫数を集計、表示する機能です。  

### 使用イメージ  
#### 部品の検索・登録・更新・棚卸し  



#### 製品の検索・登録・更新・削除  




#### 会社情報の検索・登録・更新・削除（REST API）  
https://github.com/user-attachments/assets/17c3992c-79fb-4e62-baf9-a0a1f480a2ae





### E-R図
<img width="2100" height="1889" alt="ER" src="https://github.com/user-attachments/assets/39aeb989-b7e7-4bbe-8fda-b96cd780278b" />


### APIのURL設計
| HTTPメソッド | URL | 処理内容 |
| --- | --- | --- |
| GET | /api/companies | 取引先の会社情報を全件取得します |
| GET | /api/companies/{id} | 指定したIDの会社情報を取得します |
| POST | /api/companies | 会社情報を登録します |
| PUT | /api/companies/{id} | 会社情報を更新します |
| DELETE | /api/companies/{id} | 指定した会社情報を削除します |
| --- | --- | --- |
| GET | /api/parts | 全ての部品情報を取得します |
| GET | /api/parts/{id} | 指定したIDの部品情報を取得します |
| POST | /api/parts | 部品情報を登録します |
| PUT | /api/parts/{id} | 指定したIDの部品情報を更新します |
| DELETE | /api/parts/{id} | 現在は削除できない仕様となっているため、409Conflictが返ってきます |
| GET | /api/stock/summary | 指定された期間の部品の入出庫数を集計します |
| --- | --- | --- |
| GET | /api/product | 製品情報の一覧を取得します |
| GET | /api/product/{id} | 指定したIDの製品情報を取得します |
| POST | /api/product | 製品情報を登録します |
| PUT | /api/product/{id} | 指定したIDの製品情報を更新します |
| DELETE | /api/product/{id} | 指定した製品情報を削除します |



### 力を入れたところ


### 今後の展望
#### テストコードの追加  
現在テストコードについては学習途中のため未実装ですが、今後学習を進めていく中で実装できればと考えています。

#### 棚卸し機能のアップグレード
現段階では、部品の単純な増減しか反映させることができていないため、将来的には、棚卸し差異をADJUSTとして記録する拡張を想定しています。  

#### バリデーションとエラーハンドリングの強化
誤入力や予期せぬ入力などの対策が不十分なため、入力値のチェックや例外処理を実装して対策を強化していきたいと考えています。  

#### 責務分離
Controllerに業務ロジックが集中してしまったため、より細分化し、管理しやすいクラスの実装を心掛けていきたいと思います。  

#### 認証・権限制御の実装
会社名、部品名が固定化してしまっていたり、部品を削除することができないなど、まだまだUIに不便な点が多く残っているのが現状です。ゆくゆくはログイン機能を実装し、管理者のみが部品マスタの登録・削除を行えるようなロールベース認可を実装したいと考えています。




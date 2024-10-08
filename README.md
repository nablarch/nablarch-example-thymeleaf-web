nablarch-example-thymeleaf-web
===============================

NablarchアプリケーションフレームワークとThymeleafを利用して作成したウェブExampleアプリケーションです。

### 1.動作環境
実行環境に以下のソフトウェアがインストールされている事を前提とします。
* Java Version : 17
* Maven 3.9.9以降

以下は、本手順では事前準備不要です。

|ソフトウェア|説明|
|:---|:---|
|APサーバ|このアプリケーションはJetty 12を使用しています。jetty-ee10-maven-pluginはJetty 12へのアプリケーションのデプロイ、起動を行います。|
|DBサーバ|このアプリケーションはH2 Database Engine(以下H2)を組み込んであるため、別途インストールの必要はありません。|

### 2. プロジェクトリポジトリの取得
Gitを使用している場合、アプリケーションを配置したいディレクトリにて「git clone」コマンドを実行してください。
以下、コマンドの例です。

    $mkdir c:\example
    $cd c:\example
    $git clone https://github.com/nablarch/nablarch-example-thymeleaf-web.git

Gitを使用しない場合、最新のタグからzipをダウンロードし、任意のディレクトリへ展開してください。

### 3. アプリケーションのビルドと起動
jetty-ee10-maven-pluginを実行して、アプリケーションのビルドとJetty 12の起動を行います。以下のコマンドを実行してください。

    $cd nablarch-example-thymeleaf-web
    $mvn jetty:run

起動に成功したら、ブラウザで http://localhost:8080/ を開いてください。

#### コマンドの補足
本Exampleアプリケーションを起動するまでに必要な手順は以下になります。

1. データベースのセットアップ及びエンティティクラスの作成
1. アプリケーションのビルド
1. Jetty 12の起動

これらの手順はJetty 12を起動する`jetty:run`を実行する際にすべて含まれていますが、個別に実行することもできます。

##### データベースのセットアップ及びエンティティクラスの作成

以下のコマンドを実行することでデータベースのセットアップ及びエンティティクラスの作成を行うことができます。

    $mvn generate-resources

※gspプラグインをJava 17で実行するためにはJVMオプションの指定が必要ですが、そのオプションは`.mvn/jvm.config`で指定しています。

##### アプリケーションのビルド

以下のコマンドを実行することでアプリケーションのビルドを行うことができます。

    $mvn compile

※`compile`を実行するとデータベースのセットアップ及びエンティティクラスの作成処理も行われます。

### 5. DBの確認方法

1. https://www.h2database.com/html/download.html からH2をインストールしてください。  

2. {インストールフォルダ}/bin/h2.bat を実行してください(コマンドプロンプトが開く)。  
  ※h2.bat実行中はExampleアプリケーションからDBへアクセスすることができないため、Exampleアプリケーションを停止しておいてください。

3. ブラウザから http://localhost:8082 を開き、以下の情報でH2コンソールにログインしてください。
   JDBC URLの{dbファイルのパス}には、`nablarch_example.mv.db`ファイルの格納ディレクトリまでのパスを指定してください。  
  JDBC URL：jdbc:h2:{dbファイルのパス}/nablarch_example  
  ユーザ名：NABLARCH_EXAMPLE  
  パスワード：NABLARCH_EXAMPLE

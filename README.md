# kotlin-url-shortener
System design practice

Designing a URL shortening service like Bitly involves several key components and considerations. Here's a step-by-step guide on how to approach this problem:

### 1. Requirements Gathering
#### Functional Requirements:
- Shorten a given URL.
- Redirect to the original URL when a shortened URL is accessed.
- Track the number of times a shortened URL is accessed.
- Optionally, provide user accounts to manage shortened URLs.

#### Non-Functional Requirements:
- High availability.
- Low latency for redirection.
- Scalability to handle a large number of requests.
- Security to prevent abuse.

### 2. High-Level Design
#### Components:
- **API Service:** Handles requests to shorten URLs and access shortened URLs.
- **Database:** Stores mappings between original URLs and shortened URLs.
- **Key Generation Service:** Generates unique keys for shortened URLs.
- **Redirection Service:** Redirects users to the original URL when a shortened URL is accessed.
- **Analytics Service:** Tracks usage statistics for shortened URLs.

### 3. Detailed Design
#### 3.1 URL Shortening Process
1. **Receive URL:** The user submits a long URL to be shortened.
2. **Generate Key:** A unique key is generated for the URL.
3. **Store Mapping:** The mapping between the generated key and the original URL is stored in the database.
4. **Return Shortened URL:** The shortened URL (e.g., `https://short.ly/abc123`) is returned to the user.

#### 3.2 URL Redirection Process
1. **Receive Shortened URL:** The user accesses the shortened URL.
2. **Retrieve Original URL:** The key is extracted from the shortened URL, and the original URL is retrieved from the database.
3. **Redirect:** The user is redirected to the original URL.

#### 3.3 Key Generation
- **Approaches:**
    - Random alphanumeric strings.
    - Incremental IDs converted to a different base (e.g., base62).
- **Collision Handling:** Ensure the generated key is unique. If a collision occurs, generate a new key.

### 4. Database Schema
- **URLs Table:**
    - `id` (Primary Key)
    - `original_url` (Text)
    - `shortened_key` (VARCHAR)
    - `created_at` (Timestamp)
    - `access_count` (Integer)

### 5. Scalability Considerations
#### 5.1 Database
- **Sharding:** Distribute data across multiple database instances to handle a large number of URLs.
- **Caching:** Use a caching layer (e.g., Redis) to store frequently accessed URLs and reduce database load.

#### 5.2 Load Balancing
- Distribute incoming requests across multiple servers using a load balancer.

#### 5.3 Key Generation
- **Distributed Key Generation:** Use a distributed system for generating unique keys to avoid collisions and ensure high availability.

### 6. Fault Tolerance and Reliability
- **Replication:** Replicate the database across multiple data centers for fault tolerance.
- **Backup:** Regularly back up the database to prevent data loss.
- **Monitoring and Alerts:** Set up monitoring and alerts for system health and performance.

### 7. Security
- **Rate Limiting:** Implement rate limiting to prevent abuse of the service.
- **Authentication and Authorization:** Use API keys or OAuth for user authentication and authorization.
- **Data Validation:** Validate and sanitize input URLs to prevent security vulnerabilities like SQL injection.

### 8. Analytics
- Track metrics such as the number of URL shortenings, redirections, and user activities.
- Use a time-series database (e.g., InfluxDB) to store and analyze analytics data.

### 9. Example Code and Pseudo Code
#### URL Shortening API (Python Flask Example)
```python
from flask import Flask, request, redirect
import string, random
import sqlite3

app = Flask(__name__)

def generate_key(length=6):
    characters = string.ascii_letters + string.digits
    return ''.join(random.choice(characters) for _ in range(length))

def store_url(original_url):
    key = generate_key()
    conn = sqlite3.connect('urls.db')
    cursor = conn.cursor()
    cursor.execute("INSERT INTO urls (original_url, shortened_key) VALUES (?, ?)", (original_url, key))
    conn.commit()
    conn.close()
    return key

def get_original_url(key):
    conn = sqlite3.connect('urls.db')
    cursor = conn.cursor()
    cursor.execute("SELECT original_url FROM urls WHERE shortened_key=?", (key,))
    result = cursor.fetchone()
    conn.close()
    return result[0] if result else None

@app.route('/shorten', methods=['POST'])
def shorten_url():
    original_url = request.form['url']
    key = store_url(original_url)
    return f"https://short.ly/{key}"

@app.route('/<key>', methods=['GET'])
def redirect_url(key):
    original_url = get_original_url(key)
    if original_url:
        return redirect(original_url)
    else:
        return "URL not found", 404

if __name__ == '__main__':
    app.run(debug=True)
```

This example provides a basic implementation of a URL shortening service. For a production system, you would need to add more features such as error handling, security measures, and scalability improvements.

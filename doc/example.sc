;; generate a list
(define (makelist n)
  (if (= n 0)
     (list 0)                       ; base case. Just return (0)
     (cons n (makelist (- n 1)))))  ; recursive case. Add n to the head of (n-1 n-2

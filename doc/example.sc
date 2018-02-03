;; cons and car defintions
(cons (cons 1 2) (cons 3 4))
-> ((1 . 2) 3 . 4)

(cons (cons 1 (cons 2 3)) 4)
-> ((1 2 . 3) . 4)

(cons (cons (cons 1 2) (cons 3 4)) (cons (cons 1 2) (cons 3 4)))
-> (((1 . 2) 3 . 4) (1 . 2) 3 . 4)

(cons 1 (cons 2 (cons 3 (cons 4 '()))))
-> (1 2 3 4)

(car (cons (cons 1 2) (cons 3 4)))
-> (1 . 2)

(car (cons (cons 1 (cons 2 3)) 4))
-> (1 2 . 3)


(cdr (cons (cons 1 2) (cons 3 4)))
-> (3 . 4)
(cdr (cons (cons 1 (cons 2 3)) 4))
-> 4


;; Definition with lambda
(define fact (lambda (n) (if (< n 1) 1 (* n (fact (- n 1))))))


;; generate a list
(define (makelist n)
  (if (= n 0)
     (list 0)                       ; base case. Just return (0)
     (cons n (makelist (- n 1)))))  ; recursive case. Add n to the head of (n-1 n-2

;; faculty
(define (faculty n)
    (if (= n 0)
    1
    (* n (faculty (- n 1)))))

(fakultaet 6)

;; operator as argument
(define (h op x y)
    (op x y))

(h + 23 42)
(h * 23 42)

;; branch
(if (> 3 2) ’yes ’no)
yes

(if (> 2 3) ’yes ’no)
no

;; abs function
(define (abs x)
        (if (< x 0)
            (- x)
            x))

;; Sum list of values
(define (sum list-of-values)
        (if (= 1 (length list-of-values))
            (car list-of-values)
            (+ (car list-of-values)
               (sum (cdr list-of-values)))))


> (sum (list 5 6 7))
18

;; type error
> (define (improved-code q) (* q 2))

> (define code-quality 4)

> (improved-code code-quality)
8
> (define code-quality "poor")

> (improved-code code-quality)
*: expects type  as 1st argument, given: "poor"; other arguments were: 2

;; Apply twicy a function
> (define (double value) (* 2 value))

> (define (apply-twice fn value) (fn (fn value)))

> (apply-twice double 2)
8

##################################################
;; work with eval, save a list and eval it later
; Prerequisites:
(define (swap-2-3 x)
        (list (car x)
              (caddr x)
              (cadr x)))

> (swap-2-3 (list 1 2 3))
(1 3 2)

> (swap-2-3 (list "a" "b" "c"))
("a" "c" "b")

---------

> (define four-over-two (list '/ 4 2))

> four-over-two
(/ 4 2)
> (eval four-over-two)
2
> (define switched (swap-2-3 four-over-two))

> (eval switched)
1/2
> switched
(/ 2 4)

##################################################


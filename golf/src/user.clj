(defn m[& a](doseq[s(loop[l a k[]](if(= l k)l(recur(reduce #(if(>(.compareToIgnoreCase(str(peek %))(str %2))0)(conj(pop %)%2(peek %))(conj % %2))[]l)l)))](prn s)))
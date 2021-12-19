✔ (ns piotr-yuxuan.slava.slava-record
?   "FIXME FIXME cljdoc"
?   (:require [potemkin :refer [def-map-type]])
?   (:import (org.apache.avro.generic GenericData$Record)))
  
✔ (defprotocol SlavaRecord
?   (unwrap [_]))
  
~ (def-map-type SlavaGenericRecord [generic-record mta]
✘   (get [_ k default-value] (let [{:piotr-yuxuan.slava/keys [field-decoders]} mta]
✘                              (if-let [field-getter (get field-decoders k)]
✘                                (field-getter generic-record)
✘                                default-value)))
✘   (assoc [_ k v] (SlavaGenericRecord. (.put ^GenericData$Record generic-record ^String k v) mta))
✘   (dissoc [_ k] (throw (ex-info "NotImplementedException" {:error "It is not possible to dissoc the field of an `GenericData$Record`. Try to set the value at `nil`?"})))
✘   (keys [_] (-> mta :piotr-yuxuan.slava/field-decoders keys))
✘   (meta [_] (assoc mta :piotr-yuxuan.slava/generic-record generic-record))
✘   (with-meta [_ new-mta] (SlavaGenericRecord. generic-record (merge mta new-mta)))
  
?   SlavaRecord
✘   (unwrap [_] (:piotr-yuxuan.slava/generic-record mta)))
